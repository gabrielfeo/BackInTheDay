package com.gabrielfeo.backintheday.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MovieDb {

	private static final String BASE_URL = "https://api.themoviedb.org/3/";
	private static Retrofit retrofit;

	public static MovieService getMovieService() {
		return getRetrofit().create(MovieService.class);
	}

	private static Retrofit getRetrofit() {
		if (retrofit == null) { buildRetrofit(); }
		return retrofit;
	}

	private static void buildRetrofit() {
		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

}
