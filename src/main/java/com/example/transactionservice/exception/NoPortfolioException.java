package com.example.transactionservice.exception;

public class NoPortfolioException extends RuntimeException{
    public NoPortfolioException(String message) {
        super(message);
    }
}
