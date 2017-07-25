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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        currencyConverterService = null;
        exchangeRateRepository = null;
    }

    @Test
    public void convertCurrency() throws Exception {
        when(exchangeRateRepository.findTopByNameOrderByEffectiveDateDesc(anyString()))
                .thenReturn(mockExchangeRate());

        BigDecimal amount = new BigDecimal("1000");
        String fromCurrency = "USD";
        String toCurrency = "THB";
        ConverterResponse result = currencyConverterService
                .convertCurrency(amount, fromCurrency, toCurrency);
        assertEquals("33410", result.getAmount());
    }

    private ExchangeRate mockExchangeRate() {
        ExchangeRate rate = new ExchangeRate();
        rate.setId(Long.valueOf(1));
        rate.setName("USD/THB");
        rate.setEffectiveDate(new Date());
        rate.setSymbol("THB=X");
        rate.setPrice(new BigDecimal("33.41000"));
        return rate;
    }

}