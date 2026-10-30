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
        return  amount > 0 && getAvailableCredit() >= amount;
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
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        List<AuthorizationMethod> auths = new ArrayList<>();
        auths.add(AuthorizationMethod.CCV);
        auths.add(AuthorizationMethod.PIN);
        return auths;
    }
}