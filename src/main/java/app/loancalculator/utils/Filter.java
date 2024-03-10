package app.loancalculator.utils;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filter {

    /**
     * Filters a list of months based on the specified month index range.
     *
     * @param data The list of months to filter.
     * @param from The starting index of the range (inclusive).
     * @param to The ending index of the range (inclusive).
     * @return A filtered list of months.
     */
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
