package org.fludland.userservice.exceptions;

public class ProfileNotFoundException extends ApplicationException {
    public ProfileNotFoundException() {
    }

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
