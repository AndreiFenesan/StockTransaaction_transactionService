package com.example.transactionservice.config.stock;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stock-api")
@Getter
@Setter
public class StockInfoProperties {

    private String url;
}
