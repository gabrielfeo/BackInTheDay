package com.gabrielfeo.backintheday.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielfeo.backintheday.model.Review;

import java.util.List;

public class MovieReviewsResponse {

    @JsonProperty("id")
    private final int movieId;

    @JsonProperty("results")
    private final List<Review> reviews;

    @JsonCreator
    public MovieReviewsResponse(
            @JsonProperty("id") int movieId,
            @JsonProperty("results") List<Review> reviews) {
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
