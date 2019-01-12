package com.gabrielfeo.backintheday.model;

import android.net.Uri;

import com.gabrielfeo.backintheday.data.url.MoviePosterUrl;
import com.google.gson.annotations.SerializedName;

/**
 * Placeholder class, valid until the movieDb API is integrated with the app.
 */
public class Movie {

    private final int id;

    @SerializedName("original_title")
    private final String title;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("poster_path")
    private final String posterPath;
    @SerializedName("popularity")
    private final float popularity;
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

    public Uri getPosterUrl() {
        return MoviePosterUrl.getFor(posterPath);
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

}
