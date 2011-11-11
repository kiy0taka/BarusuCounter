package barusucounter

import javafx.util.StringConverter

stage(title: 'BarusuCounter', visible: true, centerOnScreen: true) {
    scene(width: 800, height: 600) {
        borderPane {
            top {
                toolBar() {
                    button 'start', onAction: controller.start
                    button 'stop', onAction: controller.stop
                    button 'pause', onAction: controller.pause
                    button 'resume', onAction: controller.resume
                    button '<', onAction: controller.moveLeft
                    button '>', onAction: controller.moveRight
                }
            }
            center {
                stackPane {
                    lineChart {
                        numberAxis(autoRanging: false, tickUnit:1, label: "Time",
                        lowerBound: bind { model.lower }, upperBound: bind { model.upper },
                        tickLabelFormatter: [
                            toString: { new Date((long)(model.start.time + (it * 1000))).format('HH:mm:ss') }
                        ] as StringConverter)
                        numberAxis(lowerBound:0, upperBound:10, tickUnit:1, autoRanging: bind { model.max > 10 }, label: "Tweets")
                        series(name: model.search.join(', '), data: model.data)
                    }
                }
            }
        }
    }
}
