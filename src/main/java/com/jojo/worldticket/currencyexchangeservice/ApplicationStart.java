package com.jojo.worldticket.currencyexchangeservice;

import com.jojo.worldticket.currencyexchangeservice.entity.ExchangeRate;
import com.jojo.worldticket.currencyexchangeservice.model.Fields;
import com.jojo.worldticket.currencyexchangeservice.model.Resource;
import com.jojo.worldticket.currencyexchangeservice.model.YahooExchangeResponse;
import com.jojo.worldticket.currencyexchangeservice.repository.ExchangeRateRepository;
import com.jojo.worldticket.currencyexchangeservice.service.YahooExchangeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by kkosittaruk on 23/07/2017.
 */
@Component
@Slf4j
public class ApplicationStart implements CommandLineRunner {

    private final ExchangeRateRepository exchangeRateRepository;

    private final YahooExchangeProvider yahooExchangeProvider;

    @Autowired
    public ApplicationStart(ExchangeRateRepository exchangeRateRepository,
                            YahooExchangeProvider yahooExchangeProvider) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.yahooExchangeProvider = yahooExchangeProvider;
    }

    @Override
    public void run(String... args) throws Exception {
        YahooExchangeResponse exchangeRateFromYahoo = yahooExchangeProvider.getYahooExchangeRate();
        exchangeRateFromYahoo
                .getDataList().getResources()
                .forEach(this::initExchangeRate);

        exchangeRateRepository.findAll()
                .forEach(exchangeRate -> log.info(exchangeRate.toString()));
    }

    @Transactional
    private void initExchangeRate(Resource resource) {
        Fields f = resource.getResourceDetail().getFields();

        ZonedDateTime zdt = ZonedDateTime.parse(f.getUtctime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));

        ExchangeRate rate = new ExchangeRate();
        rate.setName(f.getName());
        rate.setEffectiveDate(Date.from(zdt.toInstant()));
        rate.setSymbol(f.getSymbol());
        rate.setPrice(new BigDecimal(f.getPrice()));

        exchangeRateRepository.save(rate);
    }
}
