package com.example.transactionservice.exception;


public class NotEnoughFoundsException extends RuntimeException {
    public NotEnoughFoundsException(String message) {
        super(message);
    }
}
