package org.fludland.userservcie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateProfileDto {
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String email;

    @JsonCreator
    public CreateProfileDto(
            @JsonProperty("userId")     final Long userId,
            @JsonProperty("firsName")   final String firstName,
            @JsonProperty("lastName")   final String lastName,
            @JsonProperty("email")  final String email
    ) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
    public String getEmail() {
        return email;
    }
}
