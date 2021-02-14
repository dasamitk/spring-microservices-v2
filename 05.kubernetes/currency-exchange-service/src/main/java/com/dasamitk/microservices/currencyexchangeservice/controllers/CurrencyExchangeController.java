package com.dasamitk.microservices.currencyexchangeservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dasamitk.microservices.currencyexchangeservice.beans.CurrencyExchangeBean;
import com.dasamitk.microservices.currencyexchangeservice.repositories.ICurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private ICurrencyExchangeRepository repository;

	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchangeBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		logger.info("retrieveExchangeValue called with {} to {}", from, to);

		CurrencyExchangeBean currencyExchange = repository.findByFromAndTo(from, to);

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);
		}

		String port = environment.getProperty("local.server.port");

		// CHANGE-KUBERNETES
		String host = environment.getProperty("HOSTNAME");
		String version = "v11";

		currencyExchange.setEnvironment(port + " " + version + " " + host);

		return currencyExchange;

	}

}
