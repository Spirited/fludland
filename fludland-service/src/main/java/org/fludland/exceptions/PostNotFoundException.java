package org.fludland.exceptions;

public class PostNotFoundException extends ApplicationException {
    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
