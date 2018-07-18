package com.gabrielfeo.backintheday.data.service;

import com.gabrielfeo.backintheday.data.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

	@GET("/movies/top_rated")
	Call<List<Movie>> getTopRated();

	@GET("/movies/popular")
	Call<List<Movie>> getPopular();

}
