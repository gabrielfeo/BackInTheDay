package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_trailers")
public class Trailer {

    @PrimaryKey
    @ColumnInfo(name = "video_id")
    @SerializedName("key")
    private final String videoId;

    @ColumnInfo(name = "movie_id")
    @Expose(serialize = false, deserialize = false)
    private int movieId;

    @ColumnInfo(name = "title")
    @SerializedName("name")
    private final String title;

    @ColumnInfo(name = "site_name")
    @SerializedName("site")
    private final String siteName;

    @ColumnInfo(name = "language")
    @SerializedName("iso_639_1")
    private final String language;

    @ColumnInfo(name = "country")
    @SerializedName("iso_3166_1")
    private final String country;

    public Trailer(String id, String title, String siteName,
                   String language, String country) {
        this(id, 0, title, siteName, language, country);
    }

    public Trailer(String id, int movieId, String title, String siteName,
                   String language, String country) {

        this.videoId = id;
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
