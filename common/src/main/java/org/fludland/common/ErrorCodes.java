package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ErrorCodes {
    USER_ALREADY_EXISTS_ERROR(1, "User already exists");

    private final int errorCode;
    private final String description;

    @JsonCreator
    ErrorCodes(@JsonProperty("errorCode") final int errorCode, @JsonProperty("description") final String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
