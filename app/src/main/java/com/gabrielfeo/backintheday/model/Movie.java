package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielfeo.backintheday.data.url.MoviePosterUrl;

@Entity(tableName = "movies")
public class Movie {

    @NonNull
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

    @ColumnInfo(name = "poster_path")
    @JsonProperty("poster_path")
    private final String posterPath;

    @ColumnInfo(name = "popularity")
    @JsonProperty("popularity")
    private final float popularity;

    @ColumnInfo(name = "vote_average")
    @JsonProperty("vote_average")
    private final float voteAverage;

    @ColumnInfo(name = "has_video")
    @JsonProperty("video")
    private final boolean hasVideo;

    @JsonCreator
    public Movie(
            @JsonProperty("id") int id,
            @JsonProperty("original_title") String title,
            @JsonProperty("release_date") String releaseDate,
            @JsonProperty("poster_path") String posterPath,
            @JsonProperty("popularity") float popularity,
            @JsonProperty("vote_average") float voteAverage,
            @JsonProperty("has_video") boolean hasVideo) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.hasVideo = hasVideo;
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

    public int getId() {
        return id;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Uri getPosterUrl() {
        return MoviePosterUrl.getFor(posterPath);
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

}
