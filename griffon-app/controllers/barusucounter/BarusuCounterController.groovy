package barusucounter

import twitter4j.*
import java.util.concurrent.*
import javafx.scene.chart.XYChart.Data
import javafx.application.Platform

class BarusuCounterController {
    def model
    def view
    def executor
    def twitter

    void mvcGroupInit(Map args) {
    }

    def start = {
        def start = System.currentTimeMillis()
        model.start = new Date(start)
        def list = new CopyOnWriteArrayList()
        executor = Executors.newSingleThreadScheduledExecutor()
        executor.scheduleWithFixedDelay({
            execAsync {
                def sec = (System.currentTimeMillis() - start) / 1000
                int val = list.size()
                model.data.add(new Data(sec, val))
                model.max = Math.max(val, model.max)
                if (model.scroll && sec > 7) {
                    moveRight()
                }
                list.clear()
            }
        } as Runnable, 0, 1, TimeUnit.SECONDS)
        twitter = new TwitterStreamFactory().instance
        twitter.with {
            addListener([onStatus:{ tweet ->
                println "[${tweet.createdAt.format('HH:mm:ss')}] ${tweet.text}"
                list.add(tweet)
            }, onException: { e -> e.printStackTrace() }] as UserStreamAdapter);
            filter(new FilterQuery().track(model.search))
        }
    }

    def moveLeft = {
        model.lower -= 1
        model.upper -= 1
    }

    def moveRight = {
        model.lower += 1
        model.upper += 1
    }

    def pause = {
        model.scroll = false
    }

    def resume = {
        model.scroll = true
    }

    def stop = {
        executor.shutdownNow()
        twitter.shutdown()
    }
}
