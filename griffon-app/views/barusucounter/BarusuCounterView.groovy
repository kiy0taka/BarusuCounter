package barusucounter

import javafx.scene.chart.XYChart.Data

def i = 0

stage(title: 'BarusuCounter', visible: true, centerOnScreen: true) {
    scene(width: 800, height: 600) {
        vbox() {
            lineChart {
                numberAxis(tickUnit: 1, autoRanging: false, label: "Time(s)",
                    lowerBound: bind(source:model, sourceProperty:'lower'),
                    upperBound: bind(source:model, sourceProperty:'upper'))
                numberAxis(autoRanging: true, label: "Tweets")
                series(name: '#sht', data: model.data)
            }
        }
    }
}
