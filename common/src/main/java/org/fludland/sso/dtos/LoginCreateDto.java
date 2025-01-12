package org.fludland.sso.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCreateDto {
    private final String username;
    private final String password;
    private final String email;

    @JsonCreator
    public LoginCreateDto(@JsonProperty("username")     final String username,
                          @JsonProperty("password")     final String password,
                          @JsonProperty("email")        final String email
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
