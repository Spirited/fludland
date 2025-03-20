package org.fludland.api.exceptions;

public class MissingAuthHeaderException extends ApplicationException {
    public MissingAuthHeaderException() {
    }

    public MissingAuthHeaderException(String message) {
        super(message);
    }
}
