package org.fludland.sso.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UserDetailsDto {
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastLogin;

    @JsonCreator
    public UserDetailsDto(
            @JsonProperty("userId")     final Long userId,
            @JsonProperty("username")   final String username,
            @JsonProperty("createdAt")  final LocalDateTime createdAt,
            @JsonProperty("lastLogin")  final LocalDateTime lastLogin
    ) {
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

    @JsonGetter
    public Long getUserId() {
        return userId;
    }

    @JsonGetter
    public String getUsername() {
        return username;
    }

    @JsonGetter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonGetter
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
}
