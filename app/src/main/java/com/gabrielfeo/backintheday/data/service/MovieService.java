package com.gabrielfeo.backintheday.data.service;

import com.gabrielfeo.backintheday.data.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

	@GET("movie/top_rated")
	Call<List<Movie>> getTopRated();

	@GET("movie/popular")
	Call<List<Movie>> getPopular();

}
