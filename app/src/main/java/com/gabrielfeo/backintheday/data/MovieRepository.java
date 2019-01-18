package com.gabrielfeo.backintheday.data;

import android.arch.lifecycle.LiveData;

import com.gabrielfeo.backintheday.model.Favorite;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;
import com.gabrielfeo.backintheday.model.Review;
import com.gabrielfeo.backintheday.model.Trailer;

import java.util.List;

public interface MovieRepository {

    LiveData<List<Movie>> getMoviesOfYear(int year);
    LiveData<MovieDetails> getMovieDetails(int movieId);
    LiveData<List<Trailer>> getTrailersByMovieId(int movieId);
    LiveData<List<Review>> getReviewsByMovieId(int movieId);

    LiveData<List<Favorite>> getFavorites();
    void insert(Favorite favorite);

}
