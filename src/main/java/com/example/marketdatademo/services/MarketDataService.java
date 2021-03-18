package com.example.marketdatademo.services;

import java.util.Arrays;
import java.util.List;

import com.example.marketdatademo.models.MarketData;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// ! Does not work; idea is to separate http client methods
public class MarketDataService {
  
  private MarketDataService() {}
  
  @Bean
  public static List<MarketData> getMarketData(RestTemplate restTemplate) throws RestClientException {
    // poll exchange for market data
    return Arrays.asList(
      restTemplate.getForEntity("https://exchange.matraining.com/md", MarketData[].class).getBody()
    );
  }
}
