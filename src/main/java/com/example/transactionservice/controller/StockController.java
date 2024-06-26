package com.example.transactionservice.controller;

import com.example.transactionservice.model.stock.Stock;
import com.example.transactionservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping("/{symbol}")
    public ResponseEntity<Stock> getBySymbol(@PathVariable(name = "symbol") String stockSymbol) {
//        return ResponseEntity.ok(stockService.getBySymbol(stockSymbol));
        return null;
    }


}
