package com.jojo.worldticket.currencyexchangeservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kkosittaruk on 23/07/2017.
 */
@Entity
@Data
@JsonPropertyOrder(value = {
        "currency",
        "symbol",
        "date",
        "rate"
})
public class ExchangeRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    @JsonProperty("date")
    private Date effectiveDate;

    @Column(length = 50)
    @JsonProperty("currency")
    private String name;

    @Column(length = 5)
    private String symbol;

    @Column(precision = 12, scale = 6)
    @JsonProperty("rate")
    private BigDecimal price;

}
