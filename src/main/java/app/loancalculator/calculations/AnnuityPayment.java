package app.loancalculator.calculations;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnnuityPayment extends Payment{
    private final double monthlyPayment;

    private double monthInterest;
    AnnuityPayment(int amount, float interest, int termYears, int termMonths, int postStart, int postTerm, float postInterest) {
        super(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
        monthlyPayment = amount * ((interestOnce * (Math.pow(1 + interestOnce, numberOfPayments))) /
                ((Math.pow(1 + interestOnce, numberOfPayments)) - 1));
    }



    /**
     * Overrides the calculateCredit method to calculate the credit portion of the annuity payment.
     *
     * @return The credit portion of the annuity payment.
     */
    @Override
    protected double calculateCredit() {
        return monthlyPayment - monthInterest;
    }


    /**
     * Calculates the schedule of annuity payments
     *
     * @return list of month objects containing information(monthly credit repayment, monthly interest, full monthly payment) for each month.
     */
    public ObservableList<Month> calculate() {

        ObservableList<Month> monthList = FXCollections.observableArrayList();

        for (int index = 1; index <= numberOfPayments + postTerm; index++) {
            // Postponement period
            if (index >= postStart && index < postStart + postTerm) {
                monthInterest = calculateInterest(postInterestOnce);
                monthInterest = roundValue(monthInterest);
                monthList.add(new Month(index, roundValue(currentAmount), monthInterest , 0, monthInterest));
                continue;
            }

            monthInterest = calculateInterest();
            double credit = calculateCredit();
            monthList.add(new Month(index, roundValue(currentAmount), roundValue(monthInterest), roundValue(credit), roundValue(monthlyPayment)));
            currentAmount -= credit;
        }

        return monthList;
    }
}
