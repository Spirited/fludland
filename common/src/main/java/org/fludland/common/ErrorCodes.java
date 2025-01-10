package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ErrorCodes {
    USER_ALREADY_EXISTS_ERROR(1, "User already exists"),
    POST_NOT_FOUND_EXCEPTION(2, "Post not found"),

    WRONG_LOGIN_OR_PASSWORD_ERROR(3, "Wrong login or password"),

    INTERNAL_ERROR(999999, "Internal error"),

    SUCCESS_ERROR_CODE(200, "Success");

    private final int errorCode;
    private final String description;

    @JsonCreator
    ErrorCodes(@JsonProperty("errorCode") final int errorCode, @JsonProperty("description") final String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    @JsonGetter
    public int getErrorCode() {
        return errorCode;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }
}
