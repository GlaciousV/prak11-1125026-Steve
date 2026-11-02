package model;

public class PinAuthorization implements AuthorizationStrategy {
    private String pin;

    public PinAuthorization(String pin) {
        this.pin = pin;
    }

    @Override
    public AuthorizationMethod getType() {
        return AuthorizationMethod.PIN;
    }

    @Override
    public boolean verify(String input) {
        return pin != null && pin.equals(input);
    }
}