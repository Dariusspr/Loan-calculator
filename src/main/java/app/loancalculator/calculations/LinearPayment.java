package app.loancalculator.calculations;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LinearPayment extends  Payment{
    LinearPayment(int amount,float interest, int termYears, int termMonths, int postStart, int postTerm, float postInterest) {
        super(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
    }


    public ObservableList<Month> calculate() {
        ObservableList<Month> monthList = FXCollections.observableArrayList();

        double monthlyCreditRepayment = calculateCredit();

        for (int index = 1; index <= numberOfPayments + postTerm; index++) {
            // Postponement period
            if (index >= postStart && index < postStart + postTerm) {
                double interest = roundValue(calculatePostInterest());
                monthList.add(new Month(index, roundValue(currentAmount), interest , 0, interest));
                continue;
            }

            double interest = currentAmount * interestOnce;
            monthList.add(new Month(index, roundValue(currentAmount), roundValue(interest), roundValue(monthlyCreditRepayment), roundValue(monthlyCreditRepayment + interest)));
            currentAmount -= monthlyCreditRepayment;
        }

        return monthList;
    }

}
