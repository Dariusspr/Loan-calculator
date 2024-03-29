package app.loancalculator.utils;

import app.loancalculator.models.Month;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;

public class Export {

    /**
     * Exports data from a list of months to a CSV file.
     *
     * @param fileName The name of the CSV file to export to.
     * @param monthList The list of months containing the data to export.
     */
    public static void exportToCSV(String fileName, ObservableList<Month> monthList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Nr,Paskolos likutis,Palūkanos,Kredito grąžinimas,Mėn. įmoka\n");

            for (Month month : monthList) {
                writer.append(String.valueOf(month.getIndex())).append(',');
                writer.append(String.valueOf(month.getRemainingPayment())).append(',');
                writer.append(String.valueOf(month.getInterestPayment())).append(',');
                writer.append(String.valueOf(month.getCreditRepayment())).append(',');
                writer.append(String.valueOf(month.getFullPayment())).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
