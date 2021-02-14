package com.dasamitk.microservices.currencyexchangeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasamitk.microservices.currencyexchangeservice.beans.CurrencyExchangeBean;

public interface ICurrencyExchangeRepository 
	extends JpaRepository<CurrencyExchangeBean, Long> {
	CurrencyExchangeBean findByFromAndTo(String from, String to);
}
