package com.gabrielfeo.backintheday.altclass;

import com.gabrielfeo.backintheday.net.moviedb.MovieDb;
import com.gabrielfeo.backintheday.net.moviedb.MovieService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;

public class LoggingMovieDb {

	private Retrofit retrofit;

	public LoggingMovieDb(HttpLoggingInterceptor.Logger logger,
	                      HttpLoggingInterceptor.Level httpLogLevel) {
		try {
			Class<MovieDb> movieDb = MovieDb.class;
			OkHttpClient.Builder clientBuilder = getNewClientFrom(movieDb).newBuilder();
			HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger);

			if (httpLogLevel == Level.BODY) {
				clientBuilder.addInterceptor(new ResponseBodyLoggingInterceptor(logger))
				             .addInterceptor(httpLoggingInterceptor.setLevel(Level.HEADERS));
			} else {
				clientBuilder.addInterceptor(httpLoggingInterceptor.setLevel(httpLogLevel));
			}

			retrofit = getRetrofitFrom(movieDb).newBuilder()
			                                   .client(clientBuilder.build())
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
