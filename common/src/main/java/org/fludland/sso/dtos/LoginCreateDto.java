package org.fludland.sso.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCreateDto {
    private final String username;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;

    @JsonCreator
    public LoginCreateDto(@JsonProperty("username")     final String username,
                          @JsonProperty("password")     final String password,
                          @JsonProperty("email")        final String email,
                          @JsonProperty("firstName")    final String firstName,
                          @JsonProperty("lastName")     final String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
