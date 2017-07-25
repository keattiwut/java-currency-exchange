package com.jojo.worldticket.currencyexchangeservice.controller;

import com.jojo.worldticket.currencyexchangeservice.model.ConverterResponse;
import com.jojo.worldticket.currencyexchangeservice.service.CurrencyConverterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Matchers.*;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
@RunWith(PowerMockRunner.class)
public class ExchangeControllerTest {

    @InjectMocks
    ExchangeController exchangeController;

    @Mock
    CurrencyConverterService currencyConverterService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeController).build();
    }

    @After
    public void tearDown() throws Exception {
        exchangeController = null;
        currencyConverterService = null;
        mockMvc = null;
    }

    @Test
    public void exchangeCurrency() throws Exception {
        when(currencyConverterService.convertCurrency(any(BigDecimal.class), anyString(), anyString()))
                .thenReturn(mockResult());
        mockMvc
                .perform(get("/exchange")
                        .param("amount", "10000")
                        .param("from", "USD")
                        .param("to", "THB"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private ConverterResponse mockResult() {
        return ConverterResponse.builder()
                .date(new Date())
                .base("USD")
                .expect("THB")
                .amount(new BigDecimal("330000"))
                .build();
    }

}