package org.fludland.sso.dtos;

public class AuthorizationDto {
    private final String username;
    private final String password;

    public AuthorizationDto(String username, String password) {
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
