package org.fludland.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
