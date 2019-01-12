package com.gabrielfeo.backintheday.data;

import android.arch.lifecycle.LiveData;

import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;

import java.util.List;

public interface MovieRepository {

    LiveData<List<Movie>> getMoviesOfYear(int year);
    LiveData<MovieDetails> getMovieDetails(int movieId);

}
