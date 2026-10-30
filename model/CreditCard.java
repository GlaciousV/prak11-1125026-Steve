package model;
public class CreditCard {
    private String cardNumber;
    private double limit;
    private double currentDebt;
    private String ccv;
    private String pin;

    public CreditCard(String cardNumber, double limit, String ccv, String pin) {
        this.cardNumber = cardNumber;
        this.currentDebt = 0.0;
        this.limit = limit;
        this.ccv = ccv;
        this.pin = pin;
    }

    public boolean validate(double amount) {
        return amount > 0 && this.limit >= amount;
    }

    public void pay(double amount) {
        if (validate(amount)) {
            this.limit -= amount;
            System.out.println("Pemabayaran cash beharsil , sisa saldo Credit Card : " + limit);
        } else {
            System.out.println("saldo : " + limit + " -> tidak cukup");
        }
    }

    public double getAvailableCredit() {
        return this.limit - this.currentDebt;
    }
}
