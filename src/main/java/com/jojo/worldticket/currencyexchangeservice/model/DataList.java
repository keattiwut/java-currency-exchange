package com.jojo.worldticket.currencyexchangeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "meta",
        "resources"
})
@Data
public class DataList {

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("resources")
    private List<Resource> resources = null;
}
