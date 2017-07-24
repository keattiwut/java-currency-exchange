package com.jojo.worldticket.currencyexchangeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "price",
        "symbol",
        "ts",
        "type",
        "utctime",
        "volume"
})
@Data
public class Fields {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private String price;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("ts")
    private String ts;

    @JsonProperty("type")
    private String type;

    @JsonProperty("utctime")
    private String utctime;

    @JsonProperty("volume")
    private String volume;


}
