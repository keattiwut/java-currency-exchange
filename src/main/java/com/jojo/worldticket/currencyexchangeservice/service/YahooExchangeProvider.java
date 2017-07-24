package com.jojo.worldticket.currencyexchangeservice.service;

import com.jojo.worldticket.currencyexchangeservice.model.YahooExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
@Service
public class YahooExchangeProvider {

    private static final String ENDPOINT_URL = "https://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote?format=json";

    private final RestTemplate restTemplate;

    @Autowired
    public YahooExchangeProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public YahooExchangeResponse getYahooExchangeRate() {
        YahooExchangeResponse result = restTemplate.getForObject(ENDPOINT_URL, YahooExchangeResponse.class);
        checkResult(result);
        return result;
    }

    private void checkResult(YahooExchangeResponse result) {
        if (result == null || result.getDataList().getResources().isEmpty()) {
            throw new YahooExchangeProviderException();
        }
    }
}
