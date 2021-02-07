package com.example.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.currencyexchangeservice.beans.CurrencyExchangeBean;

public interface ICurrencyExchangeRepository 
	extends JpaRepository<CurrencyExchangeBean, Long> {
	CurrencyExchangeBean findByFromAndTo(String from, String to);
}
