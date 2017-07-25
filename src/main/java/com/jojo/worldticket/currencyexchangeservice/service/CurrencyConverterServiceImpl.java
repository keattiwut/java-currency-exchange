package com.jojo.worldticket.currencyexchangeservice.service;

import com.jojo.worldticket.currencyexchangeservice.entity.ExchangeRate;
import com.jojo.worldticket.currencyexchangeservice.model.ConverterResponse;
import com.jojo.worldticket.currencyexchangeservice.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kkosittaruk on 23/07/2017.
 */
@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    public static final String BASE_CURRENCY = "USD";

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public CurrencyConverterServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public ConverterResponse convertCurrency(BigDecimal amount, String fromCurrency, String toCurrency) {

        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            return createResult(fromCurrency, toCurrency, amount);
        }

        if (fromCurrency.equalsIgnoreCase(BASE_CURRENCY)) {
            ExchangeRate rate = getRate(toCurrency);
            BigDecimal result = amount.multiply(rate.getPrice());
            return createResult(fromCurrency, toCurrency, result);
        }

        if (toCurrency.equalsIgnoreCase(BASE_CURRENCY)) {
            ExchangeRate rate = getRate(fromCurrency);
            BigDecimal result = amount.divide(rate.getPrice(), 2, BigDecimal.ROUND_HALF_UP);
            return createResult(fromCurrency, toCurrency, result);
        }

        ExchangeRate rate = getRate(fromCurrency);
        BigDecimal intermediateAmount = amount.divide(rate.getPrice(), 2, BigDecimal.ROUND_HALF_UP);

        ExchangeRate resultRate = getRate(toCurrency);
        BigDecimal result = intermediateAmount.multiply(resultRate.getPrice());
        return createResult(fromCurrency, toCurrency, result);
    }

    private ExchangeRate getRate(String currency) {
        ExchangeRate rate = exchangeRateRepository
                .findTopByNameOrderByEffectiveDateDesc(BASE_CURRENCY + "/" + currency);
        if (rate == null) {
            throw new ExchangeRateNotFoundException("Currency " + currency + " not contain on system");
        }
        return rate;
    }

    private ConverterResponse createResult(String fromCurrency, String toCurrency, BigDecimal result) {
        return ConverterResponse.builder()
                .base(fromCurrency)
                .expect(toCurrency)
                .date(new Date())
                .amount(result)
                .build();
    }
}
