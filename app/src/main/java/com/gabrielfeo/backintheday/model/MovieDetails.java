package com.gabrielfeo.backintheday.model;

import android.net.Uri;

import com.gabrielfeo.backintheday.data.url.MoviePosterUrl;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @SerializedName("id")
    private final int id;
    @SerializedName("original_title")
    private final String title;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("overview")
    private final String sinopsis;
    @SerializedName("runtime")
    private final int duration;
    @SerializedName("production_countries")
    private final List<ProductionCountry> countries;
    @SerializedName("spoken_languages")
    private final List<SpokenLanguage> languages;
    @SerializedName("credits")
    private final Credits credits;
    @SerializedName("vote_average")
    private final double rating;
    @SerializedName("poster_path")
    private final String posterPath;

    public MovieDetails(int id, String title, String releaseDate, String sinopsis, int duration,
                        List<ProductionCountry> countries, List<SpokenLanguage> languages,
                        Credits credits, double rating, String posterPath) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.sinopsis = sinopsis;
        this.duration = duration;
        this.countries = countries;
        this.languages = languages;
        this.credits = credits;
        this.rating = rating;
        this.posterPath = posterPath;
    }

    public Credits getCredits() {
        return credits;
    }

    public int getId() {
        return id;
    }

    public List<ProductionCountry> getCountries() {
        return countries;
    }

    public List<SpokenLanguage> getLanguages() {
        return languages;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return getReleaseDate().substring(0, 4);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public Uri getPosterUrl() {
        return MoviePosterUrl.getFor(posterPath);
    }

}
