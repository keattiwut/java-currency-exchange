package com.jojo.worldticket.currencyexchangeservice.service;

import com.jojo.worldticket.currencyexchangeservice.model.ConverterResponse;

import java.math.BigDecimal;

/**
 * Created by kkosittaruk on 23/07/2017.
 */
public interface CurrencyConverterService {

    ConverterResponse convertCurrency(BigDecimal base, String target, String amount);
}
