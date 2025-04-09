package org.fludland.sso.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fludland.sso.enums.UserAccountStatus;
import org.fludland.sso.enums.UserOnlineStatus;

import java.time.LocalDateTime;

public class UserDetailsDto {
    private final Long userId;
    private final String username;
    private final UserAccountStatus accountStatus;
    private final UserOnlineStatus onlineStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastLogin;

    @JsonCreator
    public UserDetailsDto(
            @JsonProperty("userId")     final Long userId,
            @JsonProperty("username")   final String username,
            @JsonProperty("accountStatus") final UserAccountStatus accountStatus,
            @JsonProperty("onlineStatus") final UserOnlineStatus onlineStatus,
            @JsonProperty("createdAt")  final LocalDateTime createdAt,
            @JsonProperty("lastLogin")  final LocalDateTime lastLogin
    ) {
        this.userId = userId;
        this.username = username;
        this.accountStatus = accountStatus;
        this.onlineStatus = onlineStatus;
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
    public UserAccountStatus getAccountStatus() {
        return accountStatus;
    }

    @JsonGetter
    public UserOnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    @JsonGetter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonGetter
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    @Override
    public String toString() {
        return "UserDetailsDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", accountStatus=" + accountStatus +
                ", onlineStatus=" + onlineStatus +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
