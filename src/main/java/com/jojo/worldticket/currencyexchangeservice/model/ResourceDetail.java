package com.jojo.worldticket.currencyexchangeservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "classname",
        "fields"
})
@Data
public class ResourceDetail {

    @JsonProperty("classname")
    private String classname;

    @JsonProperty("fields")
    private Fields fields;
}
