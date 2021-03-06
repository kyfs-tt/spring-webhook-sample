package com.example.marketdatademo.controllers.webhooks;

import com.example.marketdatademo.models.MarketData;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks")
public class MarketDataHook {
  
  private static Logger logger = LoggerFactory.getLogger(MarketDataHook.class);

  @PostMapping("/market-data")
  public void onMarketDataUpdate(@RequestBody List<MarketData> md) {
    // do something
    logger.info(md.toString());
  }
}
