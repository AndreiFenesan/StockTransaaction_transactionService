package com.example.transactionservice.service;


import com.example.transactionservice.dto.TransactionDto;
import com.example.transactionservice.exception.NoPortfolioException;
import com.example.transactionservice.exception.NotEnoughFoundsException;
import com.example.transactionservice.exception.NotEnoughStockQuantity;
import com.example.transactionservice.exception.StockNotFoundException;
import com.example.transactionservice.repository.datasource.StockPortfolioRepository;
import com.example.transactionservice.repository.model.Transaction;
import com.example.transactionservice.repository.model.StockPortfolio;
import com.example.transactionservice.repository.model.TransactionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final StockPortfolioRepository stockPortfolioRepository;
    private final StockService stockService;

    @Transactional
    public void placeBuyTransaction(int userId, TransactionDto transactionDto, String authToken) {
        log.info("Placing buy transaction for userId: {}, transaction: {}", userId, transactionDto);
        var optionalPortfolio = stockPortfolioRepository.findById(userId);
        StockPortfolio portfolio = optionalPortfolio.orElseGet(() -> initializeAccount(userId));
        var stockQuantity = transactionDto.getStockQuantity();

        var stock = stockService.getBySymbol(transactionDto.getSymbol(), authToken);
        var stockPrice = stock.getPrice();

        var totalStockPrice = stockPrice * stockQuantity;
        if (totalStockPrice <= portfolio.getBalance()) {
            log.info("UserId: {} has enough money for buy transaction: {}", userId, transactionDto);
            portfolio.setBalance(portfolio.getBalance() - totalStockPrice);
            portfolio.addBuyTransaction(Transaction.builder()
                    .stockPrice(stockPrice)
                    .symbol(stock.getSymbol())
                    .timestamp(System.currentTimeMillis())
                    .quantity(stockQuantity)
                    .type(TransactionType.BUY)
                    .build());
            stockPortfolioRepository.save(portfolio);
        } else {
            log.warn("UserId: {} hasn't enough money for buy transaction: {}", userId, transactionDto);
            throw new NotEnoughFoundsException("Not enough founds");
        }

    }

    @Transactional
    public void placeSellTransaction(int userId, TransactionDto transactionDto, String authToken) {
        var portfolio = stockPortfolioRepository
                .findById(userId)
                .orElseThrow(() -> new NoPortfolioException("No portfolio found"));

        var stockPurchased = portfolio.getStockPurchasedBySymbol(transactionDto.getSymbol())
                .orElseThrow(() -> new StockNotFoundException("You don't own this stock"));

        var stockSymbol = transactionDto.getSymbol();
        var stockPrice = stockService.getBySymbol(stockSymbol, authToken).getPrice();
        var quantity = stockPurchased.getQuantity();
        var quantityToSell = transactionDto.getStockQuantity();
        if (quantity > quantityToSell) {
            stockPurchased.setQuantity(quantity - quantityToSell);
            portfolio.addSellTransaction(Transaction.builder()
                    .symbol(stockSymbol)
                    .quantity(quantityToSell)
                    .timestamp(System.currentTimeMillis())
                    .stockPrice(stockPrice)
                    .type(TransactionType.SELL)
                    .build());
            portfolio.setBalance(portfolio.getBalance() + quantityToSell * stockPrice);
            stockPortfolioRepository.save(portfolio);
        } else {
            throw new NotEnoughStockQuantity("Not enough stock quantity");
        }

    }

    private StockPortfolio initializeAccount(Integer userId) {
        log.info("Initializing account for userId: {}", userId);
        var account = StockPortfolio.builder()
                .userId(userId)
                .balance(20000)
                .stockedBought(new ArrayList<>())
                .transactions(new ArrayList<>())
                .build();
        return stockPortfolioRepository.save(account);
    }
}
