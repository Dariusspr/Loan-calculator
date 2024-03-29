package app.loancalculator.calculations;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LinearPayment extends  Payment{
    LinearPayment(int amount,float interest, int termYears, int termMonths, int postStart, int postTerm, float postInterest) {
        super(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
    }


    /**
     * Calculates the schedule of linear payments
     *
     * @return list of month objects containing information(monthly credit repayment, monthly interest, full monthly payment) for each month.
     */
    public ObservableList<Month> calculate() {
        ObservableList<Month> monthList = FXCollections.observableArrayList();

        double monthlyCreditRepayment = calculateCredit();

        for (int index = 1; index <= numberOfPayments + postTerm; index++) {
            // Postponement period
            if (index >= postStart && index < postStart + postTerm) {
                double interest = roundValue(calculateInterest(postInterestOnce));
                monthList.add(new Month(index, roundValue(currentAmount), interest , 0, interest));
                continue;
            }

            double interest = calculateInterest();
            monthList.add(new Month(index, roundValue(currentAmount), roundValue(interest), roundValue(monthlyCreditRepayment), roundValue(monthlyCreditRepayment + interest)));
            currentAmount -= monthlyCreditRepayment;
        }

        return monthList;
    }

}
