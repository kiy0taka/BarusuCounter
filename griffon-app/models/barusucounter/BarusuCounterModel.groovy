package barusucounter


import javafx.collections.FXCollections
import groovyx.javafx.beans.FXBindable
import griffon.util.GriffonNameUtils
import javafx.scene.chart.XYChart.Data

class BarusuCounterModel {
    @FXBindable int lower = 0
    @FXBindable int upper = 10
    @FXBindable String[] search = ['11']
    @FXBindable int max = 0
    @FXBindable boolean scroll = true
    Date start = new Date()
    def data = FXCollections.observableArrayList()
    void mvcGroupInit(Map args) {
    }
}
