package com.example.transactionservice.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseError {
    private Map<String, String> errors;
}
