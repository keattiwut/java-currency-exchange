package com.jojo.worldticket.currencyexchangeservice.controller;

import com.jojo.worldticket.currencyexchangeservice.entity.ExchangeRate;
import com.jojo.worldticket.currencyexchangeservice.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kkosittaruk on 23/07/2017.
 */
@RestController
@RequestMapping("/rates")
public class RateController {

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public RateController(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @GetMapping
    public List<ExchangeRate> getExchangeRate() {
        return exchangeRateRepository.findAll();
    }

}
