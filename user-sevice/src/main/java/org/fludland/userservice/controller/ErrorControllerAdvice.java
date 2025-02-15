package org.fludland.userservice.controller;

import org.fludland.common.ErrorCodes;
import org.fludland.common.ErrorResponse;
import org.fludland.userservice.exceptions.AssignedUserIdToProfileException;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ProfileNotFoundException ex) { // 404
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ErrorCodes.PROFILE_NOT_FOUND));
    }

    @ExceptionHandler(AssignedUserIdToProfileException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(AssignedUserIdToProfileException ex) { // 400
        LOGGER.warn(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ErrorCodes.MISSED_MANDATORY_PARAMETERS));
    }
}
