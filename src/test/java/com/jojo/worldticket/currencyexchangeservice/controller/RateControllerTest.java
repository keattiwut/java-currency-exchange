package com.jojo.worldticket.currencyexchangeservice.controller;

import com.jojo.worldticket.currencyexchangeservice.entity.ExchangeRate;
import com.jojo.worldticket.currencyexchangeservice.repository.ExchangeRateRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Matchers.*;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
@RunWith(PowerMockRunner.class)
public class RateControllerTest {

    @InjectMocks
    RateController rateController;

    @Mock
    ExchangeRateRepository exchangeRateRepository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rateController).build();
    }

    @After
    public void tearDown() throws Exception {
        rateController = null;
        exchangeRateRepository = null;
        mockMvc = null;
    }

    @Test
    public void getExchangeRate() throws Exception {
        when(exchangeRateRepository.findAll()).thenReturn(mockExchangeRateData());
        mockMvc
                .perform(get("/rates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    private List<ExchangeRate> mockExchangeRateData() {
        ExchangeRate rate = new ExchangeRate();
        rate.setId(new Long(1));
        rate.setName("USD/THB");
        rate.setEffectiveDate(new Date());
        rate.setSymbol("THB=X");
        rate.setPrice(new BigDecimal("33.410000"));
        return Arrays.asList(rate);
    }

}