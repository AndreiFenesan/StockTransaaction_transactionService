package com.example.transactionservice.controller;


import com.example.transactionservice.dto.TransactionDto;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/buy")
    public ResponseEntity<Void> placeBuyTransaction(
            @RequestBody TransactionDto transactionDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @RequestHeader("x-user-id") String userId) {

        transactionService.placeBuyTransaction(Integer.parseInt(userId), transactionDto, authToken);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sell")
    public ResponseEntity<Void> placeSellTransaction(
            @RequestBody TransactionDto transactionDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @RequestHeader("x-user-id") String userId) {

        transactionService.placeSellTransaction(Integer.parseInt(userId), transactionDto, authToken);
        return ResponseEntity.ok().build();
    }
}
