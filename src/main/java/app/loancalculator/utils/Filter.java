package app.loancalculator.utils;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filter {

    public static ObservableList<Month> getFilteredMonths(ObservableList<Month> data, int from, int to) {
        int start = (from == 0) ? 0 : from - 1;
        int end = (to == 0) ? data.size() - 1: to - 1;
        ObservableList<Month> filteredList = FXCollections.observableArrayList();
        for (int i = start; i <= end; i++) {
            filteredList.add(data.get(i));
        }
        return filteredList;
    }
}
