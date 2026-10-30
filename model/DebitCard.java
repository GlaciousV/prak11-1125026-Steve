package model;

import java.util.ArrayList;
import java.util.List;

public class DebitCard implements PaymentStrategy {
    private String cardNumber;
    private double balance;
    private String pin;

    public DebitCard(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && this.balance >= amount;
    }

    @Override
    public void pay(double amount) {
        if (validate(amount)) {
            this.balance -= amount;
            System.out.println("Pemabayaran cash beharsil , sisa wallet : " + balance);
        } else {
            System.out.println("saldo : " + balance + " -> tidak cukup");
        }
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        List<AuthorizationMethod> auths = new ArrayList<>();
        auths.add(AuthorizationMethod.PIN);
        return auths;
    }
}