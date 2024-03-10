package app.loancalculator.models;

public class Month extends TimePeriod{


    public Month(int index, double remainingPayment, double interestPayment, double creditRepayment, double monthlyPayment) {
        super(index, remainingPayment, interestPayment, creditRepayment, monthlyPayment);
    }
}
