package com.example.transactionservice.repository.datasource;

import com.example.transactionservice.repository.model.StockPortfolio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPortfolioRepository extends MongoRepository<StockPortfolio, Integer> {
}
