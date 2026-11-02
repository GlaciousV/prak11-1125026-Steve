package model;

public class CcvAuthorization implements AuthorizationStrategy {
    private String ccv;

    public CcvAuthorization(String ccv) {
        this.ccv = ccv;
    }

    @Override
    public AuthorizationMethod getType() {
        return AuthorizationMethod.CCV;
    }

    @Override
    public boolean verify(String input) {
        return ccv != null && ccv.equals(input);
    }
}