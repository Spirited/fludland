package org.fludland.exceptions;

public class MessageNotFoundException extends ApplicationException {
    public MessageNotFoundException() {
    }

    public MessageNotFoundException(String message) {
        super(message);
    }
}
