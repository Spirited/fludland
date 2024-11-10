package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private final ErrorCodes errorCodes;

    @JsonCreator
    public ErrorResponse(@JsonProperty(value = "errorCode") ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    @JsonGetter
    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}

