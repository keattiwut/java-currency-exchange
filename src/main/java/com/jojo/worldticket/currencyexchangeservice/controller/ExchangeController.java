package com.jojo.worldticket.currencyexchangeservice.controller;

import com.jojo.worldticket.currencyexchangeservice.model.ConverterResponse;
import com.jojo.worldticket.currencyexchangeservice.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


/**
 * Created by kkosittaruk on 23/07/2017.
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public ExchangeController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping
    public ConverterResponse exchangeCurrency(@RequestParam("amount") BigDecimal moneyAmount,
                                              @RequestParam("from") String fromCurrency,
                                              @RequestParam("to") String toCurrency) {
        return currencyConverterService.convertCurrency(moneyAmount, fromCurrency, toCurrency);
    }

}
