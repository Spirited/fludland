package org.fludland.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fludland.userservcie.enums.Gender;

import java.time.LocalDate;

public class CreateNewAccount {
    private final String firstName;
    private final String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy")
    private final LocalDate dateOfBirth;
    private final String email;
    private final String username;
    private final String password;
    private final String confirmPassword;
    private final String phoneNumber;
    private final Gender gender;

    @JsonCreator
    public CreateNewAccount(
            @JsonProperty("firstName")          final String firstName,
            @JsonProperty("lastName")           final String lastName,
            @JsonProperty("dateOfBirth")        final LocalDate dateOfBirth,
            @JsonProperty("email")              final String email,
            @JsonProperty("username")           final String username,
            @JsonProperty("password")           final String password,
            @JsonProperty("confirmPassword")    final String confirmPassword,
            @JsonProperty("phoneNumber")        final String phoneNumber,
            @JsonProperty("gender")             final Gender gender
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }
}
