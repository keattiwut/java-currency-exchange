package com.jojo.worldticket.currencyexchangeservice.service;

import com.jojo.worldticket.currencyexchangeservice.model.YahooExchangeResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kkosittaruk on 24/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {YahooExchangeProviderConfig.class})
public class YahooExchangeProviderTest {

    @Autowired
    private YahooExchangeProvider yahooExchangeProvider;

    @After
    public void tearDown() throws Exception {
        yahooExchangeProvider = null;
    }

    @Test
    public void getYahooExchangeRate() throws Exception {
        YahooExchangeResponse result = yahooExchangeProvider.getYahooExchangeRate();
        Assert.assertNotNull(result);
        System.out.println(result.toString());
    }

}

@Configuration
class YahooExchangeProviderConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public YahooExchangeProvider yahooExchangeProvider(RestTemplate restTemplate) {
        return new YahooExchangeProvider(restTemplate);
    }
}