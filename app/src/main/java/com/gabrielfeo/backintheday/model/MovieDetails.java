package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.net.Uri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielfeo.backintheday.data.local.typeconverter.CreditsSerializer;
import com.gabrielfeo.backintheday.data.local.typeconverter.ProductionCountryListSerializer;
import com.gabrielfeo.backintheday.data.local.typeconverter.SpokenLanguageListSerializer;
import com.gabrielfeo.backintheday.data.url.MoviePosterUrl;

import java.util.List;

@Entity(tableName = "movie_details")
@TypeConverters({CreditsSerializer.class,
                 SpokenLanguageListSerializer.class,
                 ProductionCountryListSerializer.class}
)
public class MovieDetails {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @JsonProperty("id")
    private final int id;

    @ColumnInfo(name = "title")
    @JsonProperty("original_title")
    private final String title;

    @ColumnInfo(name = "release_date")
    @JsonProperty("release_date")
    private final String releaseDate;

    @ColumnInfo(name = "sinopsis")
    @JsonProperty("overview")
    private final String sinopsis;

    @ColumnInfo(name = "duration")
    @JsonProperty("runtime")
    private final int duration;

    @ColumnInfo(name = "countries")
    @JsonProperty("production_countries")
    private final List<ProductionCountry> countries;

    @ColumnInfo(name = "languages")
    @JsonProperty("spoken_languages")
    private final List<SpokenLanguage> languages;

    @ColumnInfo(name = "credits")
    @JsonProperty("credits")
    private final Credits credits;

    @ColumnInfo(name = "rating")
    @JsonProperty("vote_average")
    private final double rating;

    @ColumnInfo(name = "poster_path")
    @JsonProperty("poster_path")
    private final String posterPath;

    @JsonCreator
    public MovieDetails(
            @JsonProperty("id") int id,
            @JsonProperty("original_title") String title,
            @JsonProperty("release_date") String releaseDate,
            @JsonProperty("overview") String sinopsis,
            @JsonProperty("runtime") int duration,
            @JsonProperty("production_countries") List<ProductionCountry> countries,
            @JsonProperty("spoken_languages") List<SpokenLanguage> languages,
            @JsonProperty("credits") Credits credits,
            @JsonProperty("vote_average") double rating,
            @JsonProperty("poster_path") String posterPath) {
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

    public String getPosterPath() {
        return posterPath;
    }

}
