package org.fludland.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fludland.userservcie.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class ProfileDto {
    private final Long userId;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String phoneNumber;
    private final String email;
    private final String profileURL;
    private final List<String> profilePictureURLs;

    @JsonCreator
    public ProfileDto(
            @JsonProperty("userId")             final Long userId,
            @JsonProperty("username")           final String username,
            @JsonProperty("firstName")          final String firstName,
            @JsonProperty("lastName")           final String lastName,
            @JsonProperty("dateOfBirth")        final LocalDate dateOfBirth,
            @JsonProperty("gender")             final Gender gender,
            @JsonProperty("phoneNumber")        final String phoneNumber,
            @JsonProperty("email")              final String email,
            @JsonProperty("profileURL")         final String profileURL,
            @JsonProperty("profilePictureURLs") final List<String> profilePictureURLs
    ) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileURL = profileURL;
        this.profilePictureURLs = profilePictureURLs;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
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

    public String getProfileURL() {
        return profileURL;
    }

    public List<String> getProfilePictureURLs() {
        return profilePictureURLs;
    }
}
