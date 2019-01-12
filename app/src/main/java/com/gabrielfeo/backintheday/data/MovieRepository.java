package com.gabrielfeo.backintheday.data;

import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;

import java.util.List;

public interface MovieRepository {

    void getMoviesOfYear(int year, DataRequest<List<Movie>> request);
    void getMovieDetails(int movieId, DataRequest<MovieDetails> request);

}
