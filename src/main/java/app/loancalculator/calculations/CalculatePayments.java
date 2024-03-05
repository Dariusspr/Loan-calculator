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
          //  monthList = calculateAnnuity(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
        } else {
            LinearPayment linearPayment = new LinearPayment(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
            monthList = linearPayment.calculate();
           // monthList = calculateLinear(amount, interest, termYears, termMonths, postStart, postTerm, postInterest);
        }
        return monthList;
    }


//    private static ObservableList<Month> calculateLinear(int amount, float interestRate, int termYears, int termMonths, int postStart, int postTerm, float postInterest) {
//        ObservableList<Month> monthList = FXCollections.observableArrayList();
//
//
//        double currentAmount  = amount;
//        int numberOfPayments = termYears * MONTHS_YEAR + termMonths - postTerm;
//        double monthlyInterestRate = interestRate / MONTHS_YEAR / 100;
//        double monthlyPostInterestRate = postInterest / MONTHS_YEAR / 100;
//        double monthlyCreditRepayment = currentAmount / numberOfPayments;
//
//        for (int index = 1; index <= numberOfPayments + postTerm; index++) {
//            // Postponement period
//            if (index >= postStart && index < postStart + postTerm) {
//                double interest = currentAmount * monthlyPostInterestRate;
//                interest = round(interest, 2);
//                monthList.add(new Month(index, round(currentAmount, 2), interest , 0, interest));
//                continue;
//            }
//
//            double interest = currentAmount * monthlyInterestRate;
//            monthList.add(new Month(index, round(currentAmount, 2), round(interest, 2), round(monthlyCreditRepayment, 2), round(monthlyCreditRepayment + interest, 2)));
//            currentAmount -= monthlyCreditRepayment;
//        }
//
//        return monthList;
//    }

//    public static  ObservableList<Month> calculateAnnuity(int amount, float interestRate, int termYears, int termMonths, int postStart, int postTerm, float postInterest) {
//
//        ObservableList<Month> monthList = FXCollections.observableArrayList();
//
//        double currentAmount  = amount;
//        int numberOfPayments = termYears * MONTHS_YEAR + termMonths - postTerm;
//        double monthlyInterestRate = interestRate / MONTHS_YEAR / 100;
//        double monthlyPostInterestRate = postInterest / MONTHS_YEAR / 100;
//
//
//        double monthlyPayment = amount * ((monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))) /
//                        ((Math.pow(1 + monthlyInterestRate, numberOfPayments)) - 1));
//
//        for (int index = 1; index <= numberOfPayments + postTerm; index++) {
//            // Postponement period
//            if (index >= postStart && index < postStart + postTerm) {
//                double interest = currentAmount * monthlyPostInterestRate;
//                interest = round(interest, 2);
//                monthList.add(new Month(index, round(currentAmount, 2), interest , 0, interest));
//                continue;
//            }
//
//            double interest = currentAmount * monthlyInterestRate;
//            double credit = monthlyPayment - interest;
//            monthList.add(new Month(index, round(currentAmount, 2), round(interest, 2), round(credit, 2), round(monthlyPayment, 2)));
//            currentAmount -= credit;
//        }
//
//        return monthList;
//    }


//    private static double round(double value, int places) {
//        if (places < 0) throw new IllegalArgumentException();
//
//        BigDecimal roundedNumber = new BigDecimal(Double.toString(value));
//        roundedNumber = roundedNumber.setScale(places, RoundingMode.HALF_UP);
//        return roundedNumber.doubleValue();
//    }
}