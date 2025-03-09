package org.fludland.controllers;

import org.fludland.common.ErrorCodes;
import org.fludland.common.ErrorResponse;
import org.fludland.exceptions.MessageNotFoundException;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.exceptions.CommentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(PostNotFoundException ex) { // 404
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ErrorCodes.POST_NOT_FOUND_EXCEPTION));
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException ex) { // 404
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ErrorCodes.COMMENT_NOT_FOUND));
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotFoundException(MessageNotFoundException ex) { // 404
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ErrorCodes.MESSAGE_NOT_FOUND));
    }
}