package com.example.transactionservice.dto;


import lombok.Data;

@Data
public class TransactionDto {
    private String symbol;
    private double stockQuantity;
}
