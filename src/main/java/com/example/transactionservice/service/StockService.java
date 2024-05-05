package com.example.transactionservice.service;

import com.example.transactionservice.config.stock.StockInfoProperties;
import com.example.transactionservice.exception.StockNotFoundException;
import com.example.transactionservice.model.stock.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final RestTemplate restTemplate;
    private final StockInfoProperties stockProperties;


    public Stock getBySymbol(String stockSymbol, String authToken) {
        return getStockInfo(stockSymbol, authToken);
    }


    private Stock getStockInfo(String stockSymbol, String authToken) {
        log.info("Getting stock price for: {}", stockSymbol);
        try {
            var url = MessageFormat.format(stockProperties.getUrl(), stockSymbol);
            return restTemplate
                    .exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders(authToken)), Stock.class)
                    .getBody();
        } catch (HttpClientErrorException.NotFound e) {
            log.error("Stock {} not found", stockSymbol, e);
            throw new StockNotFoundException("Stock not found");
        } catch (Exception e) {
            log.error("Error in getting stock: {}", stockSymbol, e);
        }
        return null;
    }

    private HttpHeaders buildHeaders(String authToken) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, authToken);
        return headers;
    }

}
