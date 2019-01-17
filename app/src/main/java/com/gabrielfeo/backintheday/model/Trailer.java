package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(tableName = "movie_trailers")
public class Trailer {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "video_id")
    @JsonProperty("key")
    private final String videoId;

    @ColumnInfo(name = "movie_id")
    @JsonIgnore
    private int movieId;

    @ColumnInfo(name = "title")
    @JsonProperty("name")
    private final String title;

    @ColumnInfo(name = "site_name")
    @JsonProperty("site")
    private final String siteName;

    @ColumnInfo(name = "language")
    @JsonProperty("iso_639_1")
    private final String language;

    @ColumnInfo(name = "country")
    @JsonProperty("iso_3166_1")
    private final String country;

    @Ignore
    @JsonCreator
    public Trailer(
            @JsonProperty("key") String videoId,
            @JsonProperty("name") String title,
            @JsonProperty("site") String siteName,
            @JsonProperty("iso_639_1") String language,
            @JsonProperty("iso_3166_1") String country) {
        this(videoId, 0, title, siteName, language, country);
    }

    public Trailer(String videoId, int movieId, String title, String siteName,
                   String language, String country) {

        this.videoId = videoId;
        this.movieId = movieId;
        this.title = title;
        this.siteName = siteName;
        this.language = language;
        this.country = country;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

}
