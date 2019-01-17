package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MoviesResponse {

    @JsonProperty("results")
    private final List<Movie> moviesList;

    @JsonProperty("page")
    private final int page;

    @JsonProperty("total_pages")
    private final int totalPages;

    @JsonProperty("total_results")
    private final int totalResults;

    @JsonCreator
    public MoviesResponse(@JsonProperty("results") List<Movie> moviesList,
                          @JsonProperty("page") int page,
                          @JsonProperty("total_pages") int totalPages,
                          @JsonProperty("total_results") int totalResults) {
        this.moviesList = moviesList;
        this.page = page;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

}
