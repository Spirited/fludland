package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ErrorResponse {
    private final ErrorCodes errorCodes;

    @JsonCreator
    public ErrorResponse(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}

