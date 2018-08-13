package com.gabrielfeo.backintheday.altclass;

import com.gabrielfeo.backintheday.data.service.MovieDb;
import com.gabrielfeo.backintheday.data.service.MovieService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class LoggingMovieDb {

	private Retrofit retrofit;

	public LoggingMovieDb(HttpLoggingInterceptor.Logger logger) {
		try {
			Class<MovieDb> movieDb = MovieDb.class;
			OkHttpClient client = getNewClientFrom(movieDb)
					.newBuilder()
					.addInterceptor(new ResponseBodyLoggingInterceptor(logger))
					.addInterceptor(new HttpLoggingInterceptor(System.out::println).setLevel
							(HttpLoggingInterceptor.Level.BASIC))
					.build();
			retrofit = getRetrofitFrom(movieDb).newBuilder()
			                                   .client(client)
			                                   .build();
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}

	private OkHttpClient getNewClientFrom(Class<MovieDb> movieDb)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method getNewClient = movieDb.getDeclaredMethod("getNewClient");
		getNewClient.setAccessible(true);
		return (OkHttpClient) getNewClient.invoke(movieDb);
	}

	private Retrofit getRetrofitFrom(Class<MovieDb> movieDb)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method getRetrofit = movieDb.getDeclaredMethod("getRetrofit");
		getRetrofit.setAccessible(true);
		return (Retrofit) getRetrofit.invoke(movieDb);
	}

	public MovieService getMovieService() {
		return retrofit.create(MovieService.class);
	}

}
