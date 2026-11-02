package model;

public class OtpAuthorization implements AuthorizationStrategy {
    private String otp;

    public OtpAuthorization(String otp) {
        this.otp = otp;
    }

    @Override
    public AuthorizationMethod getType() {
        return AuthorizationMethod.OTP;
    }

    @Override
    public boolean verify(String input) {
        return otp != null && otp.equals(input);
    }
}