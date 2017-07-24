package com.jojo.worldticket.currencyexchangeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "resource"
})
@Data
public class Resource {

    @JsonProperty("resource")
    private ResourceDetail resourceDetail;
}
