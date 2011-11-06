package barusucounter

import twitter4j.*
import java.util.concurrent.*
import javafx.scene.chart.XYChart.Data
import javafx.application.Platform

class BarusuCounterController {
    def model
    def view

    void mvcGroupInit(Map args) {
        def start = System.currentTimeMillis()
        def list = new CopyOnWriteArrayList()
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay({
            Platform.runLater {
                def sec = (System.currentTimeMillis() - start) / 1000
                model.data.add(new Data(sec, list.size()))
                if (sec > 7) {
                    model.lower +=1
                    model.upper += 1
                }
                list.clear()
            }
        } as Runnable, 0, 1, TimeUnit.SECONDS)
        new TwitterStreamFactory().instance.with {
            addListener([onStatus:{list.add(it)}] as UserStreamAdapter); user('#sht')
        }
    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def anAction = { evt = null ->
        // this is how you define an action closure that is called from a view
    }
}
