package com.gabrielfeo.backintheday.data.service;

import com.gabrielfeo.backintheday.data.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

	@GET("movie/top_rated")
	Call<MoviesResponse> getTopRated();

	@GET("movie/popular")
	Call<MoviesResponse> getPopular();

}
