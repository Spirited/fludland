package org.fludland.userservcie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fludland.userservcie.enums.Gender;

import java.time.LocalDate;

public class CreateProfileDto {
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String phoneNumber;
    private final String email;
    private final Long logoId;

    @JsonCreator
    public CreateProfileDto(
            @JsonProperty("userId")         final Long userId,
            @JsonProperty("firstName")      final String firstName,
            @JsonProperty("lastName")       final String lastName,
            @JsonProperty("dateOfBirth")    final LocalDate dateOfBirth,
            @JsonProperty("gender")         final Gender gender,
            @JsonProperty("phoneNumber")    final String phoneNumber,
            @JsonProperty("email")          final String email,
            @JsonProperty("logoId")         final Long logoId
    ) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.logoId = logoId;
    }

    @JsonGetter
    public Long getUserId() {
        return userId;
    }

    @JsonGetter
    public String getFirstName() {
        return firstName;
    }

    @JsonGetter
    public String getLastName() {
        return lastName;
    }

    @JsonGetter
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonGetter
    public Gender getGender() {
        return gender;
    }

    @JsonGetter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonGetter
    public String getEmail() {
        return email;
    }

    @JsonGetter
    public Long getLogoId() {
        return logoId;
    }
}
