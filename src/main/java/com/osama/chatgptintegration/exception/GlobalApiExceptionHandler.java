package com.osama.chatgptintegration.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(GPTException.class)
    public ResponseEntity<ApiError> handleException(GPTException e, HttpServletRequest request) {
        ApiError error = new ApiError(
                "/ask",
                "Cannot complete operation due to an unknown error!",
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
