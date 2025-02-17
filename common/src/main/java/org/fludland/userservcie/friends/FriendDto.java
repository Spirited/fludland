package org.fludland.userservcie.friends;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fludland.userservcie.enums.FriendshipStatus;

public class FriendDto {
    private final Long userId;
    private final FriendshipStatus status;

    @JsonCreator
    public FriendDto(@JsonProperty("userId") final Long userId, @JsonProperty("status") final FriendshipStatus status) {
        this.userId = userId;
        this.status = status;
    }

    @JsonGetter
    public Long getUserId() {
        return userId;
    }

    @JsonGetter
    public FriendshipStatus getStatus() {
        return status;
    }
}
