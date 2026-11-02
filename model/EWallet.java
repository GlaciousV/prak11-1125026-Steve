package model;

import java.util.List;

public class EWallet implements PaymentStrategy {
    private String phoneNumber;
    private double balance;
    private String pin;

    public EWallet(String phoneNumber, double balance, String pin) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
    public boolean canPay(double amount) {
        return amount > 0;
    }

    @Override
    public void pay(double amount) {
        if (validate(amount)) {
            this.balance -= amount;
            System.out.println("Pembayaran E-Wallet berhasil, sisa saldo: " + balance);
        } else {
            System.out.println("Saldo E-Wallet: " + balance + " -> tidak cukup");
        }
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(AuthorizationMethod.PIN, AuthorizationMethod.OTP);
    }

    @Override
    public String getDetails() {
        return "E-Wallet (" + phoneNumber + ") - Saldo: " + balance;
    }
}