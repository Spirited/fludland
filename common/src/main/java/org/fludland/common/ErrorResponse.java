package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private final ErrorType errorType;

    @JsonCreator
    public ErrorResponse(
            @JsonProperty(value = "errorCode")  final ErrorType errorCode
    ) {
        this.errorType = errorCode;
    }

    @JsonGetter
    public ErrorType getErrorCodes() {
        return errorType;
    }
}

