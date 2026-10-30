package model;

import java.util.ArrayList;
import java.util.List;

public class Wallet implements PaymentStrategy {
    private String name;
    private double cashValue;

    public String getName() {
        return name;
    }

    public double getCashValue() {
        return cashValue;
    }

    public Wallet(String name, double cashValue) {
        this.name = name;
        this.cashValue = cashValue;
    }
    @Override 
    public boolean validate(double amount) {
        return amount > 0 && this.cashValue >= amount;
    }
    @Override 
    public void pay(double amount) {
        if (validate(amount)) {
            this.cashValue -= amount;
            System.out.println("Pemabayaran cash beharsil , sisa wallet : " + cashValue);
        } else {
            System.out.println("saldo : " + cashValue + " -> tidak cukup");
        }
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return new ArrayList<>();
    }
    @Override
    public String getDetails() {
        return "Wallet (" + name + ") - Saldo: " + cashValue;
    }
}