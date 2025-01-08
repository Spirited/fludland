package org.fludland.sso.exceptions;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
