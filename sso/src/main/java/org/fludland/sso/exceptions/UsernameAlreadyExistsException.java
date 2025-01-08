package org.fludland.sso.exceptions;

public class UsernameAlreadyExistsException extends ApplicationException {
    public UsernameAlreadyExistsException() {
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
