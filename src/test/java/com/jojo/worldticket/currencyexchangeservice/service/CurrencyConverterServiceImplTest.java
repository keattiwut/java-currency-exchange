package com.jojo.worldticket.currencyexchangeservice.service;

import com.jojo.worldticket.currencyexchangeservice.entity.ExchangeRate;
import com.jojo.worldticket.currencyexchangeservice.model.ConverterResponse;
import com.jojo.worldticket.currencyexchangeservice.repository.ExchangeRateRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Matchers.*;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
@RunWith(PowerMockRunner.class)
public class CurrencyConverterServiceImplTest {

    @InjectMocks
    CurrencyConverterServiceImpl currencyConverterService;

    @Mock
    ExchangeRateRepository exchangeRateRepository;

    @Test
    public void convertCurrency_withSameCurrency_ShouldReturnGivenAmount() throws Exception {
        when(exchangeRateRepository.findTopByNameOrderByEffectiveDateDesc(anyString()))
                .thenReturn(mockExchangeRate("THB", "33.41000"));

        BigDecimal amount = new BigDecimal("1000");
        String fromCurrency = "USD";
        String toCurrency = "USD";
        ConverterResponse result = currencyConverterService
                .convertCurrency(amount, fromCurrency, toCurrency);
        assertEquals(new BigDecimal("1000"), result.getAmount());
    }

    @Test
    public void convertCurrency_withBaseCurrencyToOtherCurrency_ShouldConvertToOtherCurrency() throws Exception {
        when(exchangeRateRepository.findTopByNameOrderByEffectiveDateDesc(anyString()))
                .thenReturn(mockExchangeRate("THB", "33.41000"));

        BigDecimal amount = new BigDecimal("1000");
        String fromCurrency = "USD";
        String toCurrency = "THB";
        ConverterResponse result = currencyConverterService
                .convertCurrency(amount, fromCurrency, toCurrency);
        assertEquals(new BigDecimal("33410.00000"), result.getAmount());
    }

    @Test
    public void convertCurrency_withOtherCurrencyTOBase_ShouldConvertToBaseCurrency() throws Exception {
        when(exchangeRateRepository.findTopByNameOrderByEffectiveDateDesc(anyString()))
                .thenReturn(mockExchangeRate("THB", "33.41000"));

        BigDecimal amount = new BigDecimal("33410.00000");
        String fromCurrency = "THB";
        String toCurrency = "USD";
        ConverterResponse result = currencyConverterService
                .convertCurrency(amount, fromCurrency, toCurrency);
        assertEquals(new BigDecimal("1000.00"), result.getAmount());
    }

    @Test
    public void convertCurrency_withOtherToOther_ShouldConvertToOther() throws Exception {
        when(exchangeRateRepository.findTopByNameOrderByEffectiveDateDesc(anyString()))
                .thenReturn(mockExchangeRate("JPY", "110"))
                .thenReturn(mockExchangeRate("THB", "33.41000"));

        BigDecimal amount = new BigDecimal("1000");
        String fromCurrency = "JPY";
        String toCurrency = "THB";
        ConverterResponse result = currencyConverterService
                .convertCurrency(amount, fromCurrency, toCurrency);
        assertEquals(new BigDecimal("303.6969000"), result.getAmount());
    }

    private ExchangeRate mockExchangeRate(String currency, String price) {
        ExchangeRate rate = new ExchangeRate();
        rate.setId(Long.valueOf(1));
        rate.setName("USD/"+currency);
        rate.setEffectiveDate(new Date());
        rate.setSymbol("THB=X");
        rate.setPrice(new BigDecimal(price));
        return rate;
    }

}