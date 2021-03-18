package com.example.marketdatademo;

import java.util.Arrays;

import com.example.marketdatademo.models.MarketData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MarketDataDemoApplication {

	private static Logger logger = LoggerFactory.getLogger(MarketDataDemoApplication.class);
	private static final String MARKETDATAWEBHOOKPATH = "/webhooks/market-data";

	@Autowired
	private Environment env;


	public static void main(String[] args) {
		SpringApplication.run(MarketDataDemoApplication.class, args);
	}

	@Bean
	private static RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws RestClientException {
		return args -> {

			// * Use a webhook
			// subscribe to exchange
			restTemplate.postForEntity(
				"https://exchange.matraining.com/md/subscription",
				env.getProperty("app.host").concat(MARKETDATAWEBHOOKPATH),
				null
			);

			// * Alternatively, use polling
			// poll exchange for market data
      var md = Arrays.asList(restTemplate.getForEntity("https://exchange.matraining.com/md", MarketData[].class).getBody());
      var mdMsft = restTemplate.getForObject("https://exchange.matraining.com/md/msft", MarketData.class);
			// do sth with data
			logger.info("\n\n\n");
			logger.info("All");
			logger.info(md.toString());
			logger.info("\n\n\n");
			logger.info("Microsoft");
			logger.info(mdMsft.toString());
		};
  }
}
