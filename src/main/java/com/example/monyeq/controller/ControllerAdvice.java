package com.example.monyeq.controller;
import com.example.monyeq.exception.ConfirmException;
import com.example.monyeq.exception.InputDataException;
import com.example.monyeq.exception.TransferException;
import com.example.monyeq.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.concurrent.atomic.AtomicInteger;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    private final AtomicInteger id = new AtomicInteger();

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorResponse> handleInputData(InputDataException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), incrementAndGetID()));
    }


    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleTransfer(TransferException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(), incrementAndGetID()));
    }


    @ExceptionHandler(ConfirmException.class)
    public ResponseEntity<ErrorResponse> handleConfirmation(ConfirmException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), incrementAndGetID()));
    }


    private int incrementAndGetID() {
        return id.incrementAndGet();
    }

}