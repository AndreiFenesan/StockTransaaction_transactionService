package com.example.transactionservice.model.stock;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Stock {

    private String symbol;
    private double price;
    private LocalDateTime dateTime;

}
