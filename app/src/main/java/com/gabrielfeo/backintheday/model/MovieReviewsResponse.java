package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieReviewsResponse {

    @JsonProperty("id")
    private final int movieId;

    @JsonProperty("results")
    private final List<Review> reviews;

    @JsonCreator
    public MovieReviewsResponse(int movieId, List<Review> reviews) {
        this.movieId = movieId;
        this.reviews = reviews;
        linkReviewsWithMovieId();
    }

    private void linkReviewsWithMovieId() {
        for (Review review : reviews) {
            review.setMovieId(movieId);
        }
    }

    public int getMovieId() {
        return movieId;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
