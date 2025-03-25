package org.fludland.sso.controller;

import org.fludland.common.ErrorType;
import org.fludland.common.ErrorResponse;
import org.fludland.sso.exceptions.UsernameAlreadyExistsException;
import org.fludland.sso.exceptions.WrongLoginOrPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(UsernameAlreadyExistsException ex) { // 409
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ErrorType.USER_ALREADY_EXISTS_ERROR));
    }

    @ExceptionHandler(WrongLoginOrPasswordException.class)
    public ResponseEntity<ErrorResponse> handleWrongLoginOrPasswordException(WrongLoginOrPasswordException ex) { // 403
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorType.WRONG_LOGIN_OR_PASSWORD_ERROR));
    }
}
