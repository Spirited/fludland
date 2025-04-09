package org.fludland.sso.exceptions;

public class UserAlreadyOfflineException extends ApplicationException {
    public UserAlreadyOfflineException() {
    }

    public UserAlreadyOfflineException(String message) {
        super(message);
    }
}
