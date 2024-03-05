package app.loancalculator.utils;

import app.loancalculator.models.Month;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Graph {


    public static XYChart.Series getGraphData(ObservableList<Month> list) {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();
        series.setName("Mėn. įmoka");
        for (Month m : list) {
            series.getData().add(new XYChart.Data<>(m.getIndex(), m.getFullPayment()));
        }
        return series;
    }
}
