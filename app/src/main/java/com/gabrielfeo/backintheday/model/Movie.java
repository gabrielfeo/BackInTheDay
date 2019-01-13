package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

import com.gabrielfeo.backintheday.data.url.MoviePosterUrl;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class Movie {

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

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private final String posterPath;

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    private final float popularity;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private final float voteAverage;

    public Movie(int id, String title, String releaseDate, String posterPath,
                 float popularity, float voteAverage) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
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

}
