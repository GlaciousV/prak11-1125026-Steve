package model;

import java.util.List;

public interface PaymentStrategy {
    boolean validate(double amount);
    void pay(double amount);
    List<AuthorizationStrategy> getRequiredAuthorizations();
    String getDetails();
    boolean canPay(double amount);
}