package com.gabrielfeo.backintheday.altclass;

import com.gabrielfeo.backintheday.data.service.MovieDb;
import com.gabrielfeo.backintheday.data.service.MovieService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSource;
import retrofit2.Retrofit;

public class ResponseLoggingMovieDb {

	private Retrofit retrofit;

	public ResponseLoggingMovieDb(HttpLoggingInterceptor.Logger logger) {
		try {
			Class<MovieDb> movieDb = MovieDb.class;
			OkHttpClient client = getNewClientFrom(movieDb)
					.newBuilder()
					.addInterceptor(getBodyLoggingInterceptor(logger))
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
		Method getNewClient = movieDb.getDeclaredMethod("getNewClient", null);
		getNewClient.setAccessible(true);
		return (OkHttpClient) getNewClient.invoke(movieDb, null);
	}

	private Interceptor getBodyLoggingInterceptor(HttpLoggingInterceptor.Logger logger) {
		return chain -> {
			Response response = chain.proceed(chain.request());
			if (response.body() != null) {
				BufferedSource bodySource = response.body().source();
				bodySource.request(Integer.MAX_VALUE);
				String responseBody = bodySource.buffer().snapshot().utf8();
				logger.log(responseBody);
			}
			return response;
		};
	}

	private Retrofit getRetrofitFrom(Class<MovieDb> movieDb)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method getRetrofit = movieDb.getDeclaredMethod("getRetrofit", null);
		getRetrofit.setAccessible(true);
		return (Retrofit) getRetrofit.invoke(movieDb, null);
	}

	public MovieService getMovieService() {
		return retrofit.create(MovieService.class);
	}

}
