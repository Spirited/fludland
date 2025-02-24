package org.fludland.exceptions;

public class CommentNotFoundException extends ApplicationException {
    public CommentNotFoundException() {
        super();
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}