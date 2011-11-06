package barusucounter


import javafx.collections.FXCollections
import groovyx.javafx.beans.FXBindable
import griffon.util.GriffonNameUtils
import javafx.scene.chart.XYChart.Data

class BarusuCounterModel {
    @FXBindable int lower = 0
    @FXBindable int upper = 10
    def data = FXCollections.observableArrayList()
    void mvcGroupInit(Map args) {
    }
}
