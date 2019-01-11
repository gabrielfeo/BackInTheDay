package com.gabrielfeo.backintheday.net.moviedb;

import com.gabrielfeo.backintheday.data.model.MovieDetails;
import com.gabrielfeo.backintheday.data.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

	@GET("movie/top_rated")
	Call<MoviesResponse> getTopRatedMovies();

	@GET("discover/movie?sort_by=popularity.desc")
	Call<MoviesResponse> getMoviesOfYear(@Query("primary_release_year") int year);

	@GET("movie/{movie_id}" + "?append_to_response=credits")
	Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId);

}
