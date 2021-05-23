package com.alex.microservices.currencyconversionservice.service;

import com.alex.microservices.currencyconversionservice.beans.CurrencyConversion;
import com.alex.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyConversionService {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

//
//    public CurrencyConversion processConversion(String from, String to, BigDecimal quantity) {
//
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
//                    .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
//                    CurrencyConversion.class, uriVariables);
//
//        CurrencyConversion currencyConversion = responseEntity.getBody();
//        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
//                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
//    }
    public CurrencyConversion processConversion(String from, String to, BigDecimal quantity) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchange(from, to);
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
    }
}
