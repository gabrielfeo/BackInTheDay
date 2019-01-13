package com.gabrielfeo.backintheday.model;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("name")
    private final String title;

    @SerializedName("key")
    private final String id;

    @SerializedName("site")
    private final String siteName;

    @SerializedName("iso_639_1")
    private final String languageAbbreviation;

    @SerializedName("iso_3166_1")
    private final String countryAbbreviation;

    public Video(String title, String id, String siteName, String languageAbbreviation,
                 String countryAbbreviation) {

        this.title = title;
        this.id = id;
        this.siteName = siteName;
        this.languageAbbreviation = languageAbbreviation;
        this.countryAbbreviation = countryAbbreviation;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getLanguageAbbreviation() {
        return languageAbbreviation;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }
}
