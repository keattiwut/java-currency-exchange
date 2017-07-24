package com.jojo.worldticket.currencyexchangeservice.repository;

import com.jojo.worldticket.currencyexchangeservice.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kkosittaruk on 23/07/2017.
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findTopByNameOrderByEffectiveDateDesc(String name);
}
