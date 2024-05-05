package com.example.transactionservice.controller.advice;

import com.example.transactionservice.dto.error.ResponseError;
import com.example.transactionservice.exception.NotEnoughFoundsException;
import com.example.transactionservice.exception.NotEnoughStockQuantity;
import com.example.transactionservice.exception.StockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorsController {


    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ResponseError> handleStockNotFound(StockNotFoundException stockNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(Map.of("Error", stockNotFoundException.getMessage())));
    }

    @ExceptionHandler(NotEnoughFoundsException.class)
    public ResponseEntity<ResponseError> handleStockNotFound(NotEnoughFoundsException notEnoughFoundsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(Map.of("Error", notEnoughFoundsException.getMessage())));
    }

    @ExceptionHandler(NotEnoughStockQuantity.class)
    public ResponseEntity<ResponseError> handleStockNotFound(NotEnoughStockQuantity notEnoughStockQuantity) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(Map.of("Error", notEnoughStockQuantity.getMessage())));
    }
}
