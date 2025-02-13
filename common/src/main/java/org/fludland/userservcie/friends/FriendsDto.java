package org.fludland.userservcie.friends;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FriendsDto {
    private final List<Long> friends;

    @JsonCreator
    public FriendsDto(@JsonProperty("friends") List<Long> friends) {
        this.friends = friends;
    }

    @JsonGetter
    public List<Long> getFriends() {
        return friends;
    }
}
