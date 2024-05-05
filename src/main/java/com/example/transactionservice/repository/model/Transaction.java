package com.example.transactionservice.repository.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
public class Transaction {

    private String id;
    private long timestamp;
    private String symbol;
    private double stockPrice;
    private double quantity;
    private TransactionType type;
}
