package app.loancalculator.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class Payment {
    protected final int paymentFrequency = 12; // Every month
    private final int decimalPlaces = 2;
    protected double currentAmount, interestOnce, postInterestOnce;
    protected int amount, termYears, termMonths, postStart, postTerm;
    protected float interestRate, postInterest;
    protected final int numberOfPayments;

    Payment(int amount, float interest, int termYears, int termMonths, int postStart, int postTerm, float postInterest) {
        this.amount = amount;
        this.interestRate = interest;
        this.termYears = termYears;
        this.termMonths = termMonths;
        this.postStart = postStart;
        this.postTerm = postTerm;
        this.postInterest = postInterest;

        currentAmount = amount;
        interestOnce = interestRate / paymentFrequency / 100;
        postInterestOnce = postInterest / paymentFrequency / 100;
        numberOfPayments = termMonths + termYears * paymentFrequency - postTerm;
    }

    protected double roundValue(double value) {
        BigDecimal roundedNumber = new BigDecimal(Double.toString(value));
        roundedNumber = roundedNumber.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return roundedNumber.doubleValue();
    }


    protected double calculateCredit() {
        return currentAmount / numberOfPayments;
    }

    protected double calculatePostInterest() {
        return currentAmount * postInterestOnce;
    }
}

