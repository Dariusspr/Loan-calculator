package app.loancalculator.models;

abstract class TimePeriod {
    protected int index;
    protected  double remainingPayment;
    protected double interestPayment;
    protected double creditRepayment;
    protected double fullPayment;


    /**
     * Object containing information about loan payment
     * @param index month number
     * @param remainingPayment
     * @param interestPayment
     * @param creditRepayment
     * @param fullPayment
     */
    protected TimePeriod(int index,double remainingPayment, double interestPayment, double creditRepayment, double fullPayment) {
        this.index = index;
        this.remainingPayment = remainingPayment;
        this.interestPayment = interestPayment;
        this.creditRepayment = creditRepayment;
        this.fullPayment = fullPayment;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getRemainingPayment() {
        return remainingPayment;
    }

    public void setRemainingPayment(int remainingPayment) {
        this.remainingPayment = remainingPayment;
    }

    public double getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(int interestPayment) {
        this.interestPayment = interestPayment;
    }

    public double getCreditRepayment() {
        return creditRepayment;
    }

    public void setCreditRepayment(int creditRepayment) {
        this.creditRepayment = creditRepayment;
    }

    public double getFullPayment() {
        return fullPayment;
    }

    public void setFullPayment(int fullPayment) {
        this.fullPayment = fullPayment;
    }

}
