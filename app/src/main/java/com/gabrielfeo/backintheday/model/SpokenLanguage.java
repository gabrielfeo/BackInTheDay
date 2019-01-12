package com.gabrielfeo.backintheday.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;
import java.util.MissingResourceException;

public class SpokenLanguage {

    @SerializedName("iso_639_1")
    private final String abbreviation;
    @SerializedName("name")
    private final String name;

    public SpokenLanguage(String abbreviation, String name) {
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
