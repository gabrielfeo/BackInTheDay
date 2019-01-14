package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;
import java.util.MissingResourceException;

public class SpokenLanguage {

    @JsonProperty("iso_639_1")
    private final String abbreviation;

    @JsonProperty("name")
    private final String name;

    @JsonCreator
    public SpokenLanguage(
            @JsonProperty("iso_639_1") String abbreviation,
            @JsonProperty("name") String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation(boolean threeLetter) {
        String abbreviation = this.abbreviation;
        if (threeLetter) {
            try {
                abbreviation = getLocale().getISO3Language();
            } catch (MissingResourceException exception) {
                exception.printStackTrace();
            }
        }
        return abbreviation;
    }

    public Locale getLocale() {
        return new Locale(this.abbreviation);
    }

}
