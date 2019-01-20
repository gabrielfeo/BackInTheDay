package com.gabrielfeo.backintheday.data.moviedb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.gabrielfeo.backintheday.BuildConfig;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public final class MovieDb {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String MOVIEDB_API_KEY = BuildConfig.MOVIEDB_API_KEY;
    public static final int STANDARD_PAGE_SIZE = 20;
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
                .addConverterFactory(getNewConverterFactory())
                .build();
    }

    private static OkHttpClient getNewClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(getApiKeyInterceptor());
        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));
        return clientBuilder.build();
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

    private static Converter.Factory getNewConverterFactory() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return JacksonConverterFactory.create(mapper);
    }

}
