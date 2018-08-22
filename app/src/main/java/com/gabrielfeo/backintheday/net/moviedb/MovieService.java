package com.gabrielfeo.backintheday.net.moviedb;

import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

	@GET("movie/top_rated")
	Call<MoviesResponse> getTopRated();

	@GET("movie/popular")
	Call<MoviesResponse> getPopular();

	@GET("movie/{movie_id}")
	Call<Movie> getMovieWithId(@Path("movie_id") int movieId);

}
