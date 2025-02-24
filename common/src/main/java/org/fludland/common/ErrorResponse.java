package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.fludland.common.ErrorCodes.SUCCESS_ERROR_CODE;

public class ErrorResponse {
    private final String message;
    private final ErrorCodes errorCodes;

    @JsonCreator
    public ErrorResponse(
            @JsonProperty(value = "message")    final String message,
            @JsonProperty(value = "errorCode")  final ErrorCodes errorCodes
    ) {
        this.message = message;
        this.errorCodes = errorCodes;
    }

    @JsonCreator
    public ErrorResponse(
            @JsonProperty(value = "message")  final String message
    ) {
        this(message, SUCCESS_ERROR_CODE);
    }

    @JsonCreator
    public ErrorResponse(
            @JsonProperty(value = "errorCode")  final ErrorCodes errorCode
    ) {
        this(null, errorCode);
    }

    @JsonGetter
    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public String getMessage() {
        return message;
    }
}

