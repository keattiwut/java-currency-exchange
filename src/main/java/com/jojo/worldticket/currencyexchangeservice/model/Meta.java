package com.jojo.worldticket.currencyexchangeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "start",
        "count"
})
@Data
public class Meta {

    @JsonProperty("type")
    private String type;

    @JsonProperty("start")
    private int start;

    @JsonProperty("count")
    private int count;
}
