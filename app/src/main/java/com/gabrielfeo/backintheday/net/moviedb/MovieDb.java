package com.gabrielfeo.backintheday.net.moviedb;

import com.gabrielfeo.backintheday.BuildConfig;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MovieDb {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String MOVIEDB_API_KEY = BuildConfig.MOVIEDB_API_KEY;
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
                .client(getNewClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient getNewClient() {
        return new OkHttpClient.Builder().addInterceptor(getApiKeyInterceptor()).build();
    }

    private static Interceptor getApiKeyInterceptor() {
        return chain -> {
            Request oldRequest = chain.request();
            HttpUrl originalUrl = oldRequest.url();
            HttpUrl newUrl = originalUrl.newBuilder()
                                        .addQueryParameter("api_key", MOVIEDB_API_KEY)
                                        .build();
            Request newRequest = oldRequest.newBuilder().url(newUrl).build();
            return chain.proceed(newRequest);
        };
    }

}
