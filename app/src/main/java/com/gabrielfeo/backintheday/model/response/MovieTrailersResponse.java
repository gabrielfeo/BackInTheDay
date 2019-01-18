package com.gabrielfeo.backintheday.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielfeo.backintheday.model.Trailer;

import java.util.List;

public class MovieTrailersResponse {

    @JsonProperty("id")
    private final int movieId;

    @JsonProperty("results")
    private final List<Trailer> trailers;

    @JsonCreator
    public MovieTrailersResponse(
            @JsonProperty("id") int movieId,
            @JsonProperty("results") List<Trailer> videos) {
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
