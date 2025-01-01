package org.fludland.exceptions;

public class ImageNotFoundException extends ApplicationException {
    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
