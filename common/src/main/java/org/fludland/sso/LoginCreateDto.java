package org.fludland.sso;

public class LoginCreateDto {
    private final String username;
    private final String password;

    public LoginCreateDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
