package org.fludland.userservice.exceptions;

public class ApplicationException extends RuntimeException {
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
