package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private final ErrorType errorType;

    @JsonCreator
    public ErrorResponse(
            @JsonProperty(value = "errorType")  final ErrorType errorType
    ) {
        this.errorType = errorType;
    }

    @JsonGetter
    public ErrorType getErrorType() {
        return errorType;
    }
}

