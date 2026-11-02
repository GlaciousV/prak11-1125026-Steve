package model;

import java.util.ArrayList;
import java.util.List;

public class CreditCard implements PaymentStrategy {
    private String cardNumber;
    private double limit;
    private double currentDebt;
    private String ccv;
    private String pin;

    public CreditCard(String cardNumber, double limit, String ccv, String pin) {
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.currentDebt = 0.0;
        this.ccv = ccv;
        this.pin = pin;
    }

    public double getAvailableCredit() {
        return this.limit - this.currentDebt;
    }

    public String getCcv() {
        return ccv;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean validate(double amount) {
        // return amount > 0 && (this.limit - this.currentDebt) >= amount;
        return amount > 0 && getAvailableCredit() >= amount;
    }

    @Override
    public void pay(double amount) {
        if (validate(amount)) {
            this.currentDebt += amount;
            this.limit -= amount;
            System.out.println("Pemabayaran cash beharsil , sisa saldo Credit Card : " + limit);
        } else {
            System.out.println("saldo : " + limit + " -> tidak cukup");
        }
    }

    @Override
    public List<AuthorizationStrategy> getRequiredAuthorizations() {
        List<AuthorizationStrategy> auths = new ArrayList<>();
        auths.add(new CcvAuthorization(this.ccv));
        auths.add(new PinAuthorization(this.pin));
        return auths;
    }

    @Override
    public String getDetails() {
        return "Credit Card (" + cardNumber + ") - Limit Tersedia: " + getAvailableCredit();
    }

    // 6
    @Override
    public boolean canPay(double amount) {
        return amount >= 100000.0 && amount <= 100000000.0;
    }
}