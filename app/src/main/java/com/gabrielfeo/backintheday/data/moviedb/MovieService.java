package com.gabrielfeo.backintheday.data.moviedb;

import com.gabrielfeo.backintheday.model.MovieDetails;
import com.gabrielfeo.backintheday.model.response.MovieReviewsResponse;
import com.gabrielfeo.backintheday.model.response.MovieTrailersResponse;
import com.gabrielfeo.backintheday.model.response.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("discover/movie?sort_by=popularity.desc")
    Call<MoviesResponse> getMoviesOfYear(@Query("primary_release_year") int year);

    @GET("movie/{movie_id}" + "?append_to_response=credits")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/videos")
    Call<MovieTrailersResponse> getTrailersByMovieId(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviewsResponse> getReviewsByMovieId(@Path("movie_id") int movieId);

}
