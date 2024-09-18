package org.fludland.sso;

public class SuccessfulRegistration {
    private final String token;

    public SuccessfulRegistration(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
