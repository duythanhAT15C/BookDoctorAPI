package com.lab.Assignment03.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionAndSuccessHandle {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<Response> handleException(String message, HttpStatus status) {
        Response error = new Response();
        error.setStatus(status.value());
        error.setMessage(message);
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, status);
    }

    public ResponseEntity<Response> handleSuccess(String message, HttpStatus status) {
        Response error = new Response();
        error.setStatus(status.value());
        error.setMessage(message);
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, status);
    }
}
