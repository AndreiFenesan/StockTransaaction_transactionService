package com.example.transactionservice.exception;

public class NotEnoughStockQuantity extends RuntimeException {
    public NotEnoughStockQuantity(String message) {
        super(message);
    }
}
