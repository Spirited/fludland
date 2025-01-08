package org.fludland.sso.exceptions;

public class WrongLoginOrPasswordException extends ApplicationException {
    public WrongLoginOrPasswordException() {
    }

    public WrongLoginOrPasswordException(String message) {
        super(message);
    }
}
