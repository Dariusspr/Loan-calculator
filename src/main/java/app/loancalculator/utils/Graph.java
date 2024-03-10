package app.loancalculator.utils;

import app.loancalculator.models.Month;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Graph {


    /**
     * Retrieves data(month index, full monthly payment) for a graph from a list of months.
     *
     * @param list The list of months from which to retrieve data.
     * @return A series of data points for the graph.
     */
    public static XYChart.Series getGraphData(ObservableList<Month> list) {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();
        series.setName("Mėn. įmoka");
        for (Month m : list) {
            series.getData().add(new XYChart.Data<>(m.getIndex(), m.getFullPayment()));
        }
        return series;
    }
}
