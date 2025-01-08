package org.fludland.sso.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessfulResult {
    private final String token;

    @JsonCreator
    public SuccessfulResult(@JsonProperty("token") String token) {
        this.token = token;
    }

    @JsonGetter
    public String getToken() {
        return token;
    }
}
