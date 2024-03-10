package app.loancalculator.calculations;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CalculatePayments {

    public static ObservableList<Month> getMonthList(int amount, float interest, int termYears, int termMonths, int postStart, int postTerm, float postInterest, boolean annuity) {
        ObservableList<Month> monthList;
        if (annuity) {
            AnnuityPayment annuityPayment = new AnnuityPayment(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
            monthList = annuityPayment.calculate();
        } else {
            LinearPayment linearPayment = new LinearPayment(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
            monthList = linearPayment.calculate();
        }
        return monthList;
    }
}