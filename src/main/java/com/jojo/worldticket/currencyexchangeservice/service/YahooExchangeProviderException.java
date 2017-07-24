package com.jojo.worldticket.currencyexchangeservice.service;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
class YahooExchangeProviderException extends RuntimeException {

    public YahooExchangeProviderException() {
        super("Cannot retrieve exchangeCurrency rate from YAHOO");
    }
}
