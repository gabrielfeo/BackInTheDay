package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

import com.gabrielfeo.backintheday.data.url.MoviePosterUrl;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "movie_details")
public class MovieDetails {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private final int id;

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    private final String title;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private final String releaseDate;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private final String sinopsis;

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    private final int duration;

    @ColumnInfo(name = "production_countries")
    @SerializedName("production_countries")
    private final List<ProductionCountry> countries;

    @ColumnInfo(name = "spoken_languages")
    @SerializedName("spoken_languages")
    private final List<SpokenLanguage> languages;

    @ColumnInfo(name = "credits")
    @SerializedName("credits")
    private final Credits credits;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private final double rating;

    @ColumnInfo(name = "poster_path")
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

    public String getPosterPath() {
        return posterPath;
    }

}
