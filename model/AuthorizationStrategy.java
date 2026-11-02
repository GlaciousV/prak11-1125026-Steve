package model;

public interface AuthorizationStrategy {
    AuthorizationMethod getType();
    boolean verify(String input);
}