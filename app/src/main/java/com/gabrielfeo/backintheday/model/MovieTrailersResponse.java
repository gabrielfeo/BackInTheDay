package com.gabrielfeo.backintheday.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailersResponse {

    @SerializedName("id")
    private final int movieId;

    @SerializedName("results")
    private final List<Trailer> trailers;

    public MovieTrailersResponse(int movieId, List<Trailer> videos) {
        this.movieId = movieId;
        this.trailers = videos;
        linkTrailersWithMovieId();
    }

    private void linkTrailersWithMovieId() {
        for (Trailer video : trailers) {
            video.setMovieId(movieId);
        }
    }

    public int getMovieId() {
        return movieId;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

}
