package com.example.monyeq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorReponse> handleException(InputDataException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorReponse.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorReponse> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorReponse.builder().message(e.getMessage()).build());
    }
}
