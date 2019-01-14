package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountry {

    @JsonProperty("iso_3166_1")
    private final String abbreviation;

    @JsonProperty("name")
    private final String name;

    @JsonCreator
    public ProductionCountry(
            @JsonProperty("iso_3166_1") String abbreviation,
            @JsonProperty("name") String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

}
