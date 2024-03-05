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


    @Override
    protected double calculateCredit() {
        return monthlyPayment - monthInterest;
    }


    public ObservableList<Month> calculate() {

        ObservableList<Month> monthList = FXCollections.observableArrayList();

        double currentAmount  = amount;
        int numberOfPayments = termYears * paymentFrequency + termMonths - postTerm;
        double monthlyInterestRate = interestRate / paymentFrequency / 100;
        double monthlyPostInterestRate = postInterest / paymentFrequency / 100;


        double monthlyPayment = amount * ((monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))) /
                ((Math.pow(1 + monthlyInterestRate, numberOfPayments)) - 1));

        for (int index = 1; index <= numberOfPayments + postTerm; index++) {
            // Postponement period
            if (index >= postStart && index < postStart + postTerm) {
                monthInterest = currentAmount * monthlyPostInterestRate;
                monthInterest = roundValue(monthInterest);
                monthList.add(new Month(index, roundValue(currentAmount), monthInterest , 0, monthInterest));
                continue;
            }

            monthInterest = currentAmount * monthlyInterestRate;
            double credit = calculateCredit();
            monthList.add(new Month(index, roundValue(currentAmount), roundValue(monthInterest), roundValue(credit), roundValue(monthlyPayment)));
            currentAmount -= credit;
        }

        return monthList;
    }
}
