package com.jojo.worldticket.currencyexchangeservice.service;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
class ExchangeRateNotFoundException extends RuntimeException {

    public ExchangeRateNotFoundException(String s) {
        super(s);
    }
}
