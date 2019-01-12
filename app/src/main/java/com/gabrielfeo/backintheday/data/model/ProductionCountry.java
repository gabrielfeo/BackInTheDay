package com.gabrielfeo.backintheday.data.model;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry {

    @SerializedName("iso_3166_1")
    private final String abbreviation;
    @SerializedName("name")
    private final String name;

    public ProductionCountry(String abbreviation, String name) {
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
