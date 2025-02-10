package org.fludland.userservcie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fludland.userservcie.enums.Gender;

import java.time.LocalDate;

public class UpdateProfileDto {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String phoneNumber;
    private final String email;
    private final Long logoId;

    @JsonCreator
    public UpdateProfileDto(
            @JsonProperty("firstName")      final String firstName,
            @JsonProperty("lastName")       final String lastName,
            @JsonProperty("dateOfBirth")    final LocalDate dateOfBirth,
            @JsonProperty("gender")         final Gender gender,
            @JsonProperty("phoneNumber")    final String phoneNumber,
            @JsonProperty("email")          final String email,
            @JsonProperty("logoId")         final Long logoId
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.logoId = logoId;
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

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Long getLogoId() {
        return logoId;
    }
}
