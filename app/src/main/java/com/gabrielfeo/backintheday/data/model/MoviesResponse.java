package com.gabrielfeo.backintheday.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("results")
    private final List<Movie> moviesList;

    @SerializedName("page")
    private final int page;

    @SerializedName("total_pages")
    private final int totalPages;

    @SerializedName("total_results")
    private final int totalResults;

    public MoviesResponse(List<Movie> moviesList, int page, int totalPages, int totalResults) {
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
