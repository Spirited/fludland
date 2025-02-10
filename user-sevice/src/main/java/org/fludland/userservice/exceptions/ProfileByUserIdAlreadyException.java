package org.fludland.userservice.exceptions;

public class ProfileByUserIdAlreadyException extends ApplicationException {
    public ProfileByUserIdAlreadyException() {
    }

    public ProfileByUserIdAlreadyException(String message) {
        super(message);
    }
}
