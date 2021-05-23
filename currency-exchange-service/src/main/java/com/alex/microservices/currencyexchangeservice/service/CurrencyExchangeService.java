package com.alex.microservices.currencyexchangeservice.service;

import com.alex.microservices.currencyexchangeservice.beans.CurrencyExchange;
import com.alex.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    public CurrencyExchange getCurrencyExchange(String from, String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange != null) {
            String port = environment.getProperty("local.server.port");
            currencyExchange.setEnvironment(port);
            return currencyExchange;
        }
        throw new RuntimeException("Unable to find data");
    }
}
