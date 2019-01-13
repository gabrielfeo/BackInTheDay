package com.gabrielfeo.backintheday.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.data.callback.SuccessCallback;
import com.gabrielfeo.backintheday.data.local.MovieCache;
import com.gabrielfeo.backintheday.data.local.MovieCacheDao;
import com.gabrielfeo.backintheday.data.moviedb.ApiResponseHandler;
import com.gabrielfeo.backintheday.data.moviedb.MovieDb;
import com.gabrielfeo.backintheday.data.moviedb.MovieService;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;
import com.gabrielfeo.backintheday.model.MoviesResponse;

import java.util.List;

public class AppMovieRepository implements MovieRepository {

    private static final String TAG = AppMovieRepository.class.getSimpleName();
    private static final AppMovieRepository instance = new AppMovieRepository();
    private final MovieService remote = MovieDb.getMovieService();
    private MovieCacheDao cache;

    private static final class InstanceHolder {
        private static final AppMovieRepository INSTANCE = new AppMovieRepository();
    }

    public static AppMovieRepository getInstance(Context applicationContext) {
        InstanceHolder.INSTANCE.cache = MovieCache.getInstance(applicationContext).dao();
        return InstanceHolder.INSTANCE;
    }


    // @formatter:off
    private final SuccessCallback<MoviesResponse> moviesByYearSuccessCallback =
            response -> cache.insert(response.getMoviesList());
    private final ErrorCallback moviesByYearErrorCallback =
            () -> logError("Error getting movies from remote");
    @Override                                                                                       // @formatter:on
    public LiveData<List<Movie>> getMoviesOfYear(int year) {
        remote.getMoviesOfYear(year)
              .enqueue(new ApiResponseHandler<>(moviesByYearSuccessCallback, moviesByYearErrorCallback));
        return cache.getMoviesOfYear(year);
    }


    // @formatter:off
    private final SuccessCallback<MovieDetails> moviesDetailsSuccessCallback =
            (movieDetails) -> cache.insert(movieDetails);
    private final ErrorCallback moviesDetailsErrorCallback =
            () -> logError("Error getting movie details from remote");
    @Override                                                                                       // @formatter:on
    public LiveData<MovieDetails> getMovieDetails(int movieId) {
        remote.getMovieDetails(movieId)
              .enqueue(new ApiResponseHandler<MovieDetails>(moviesDetailsSuccessCallback, moviesDetailsErrorCallback));
        return cache.getMovieDetails(movieId);
    }


    private void logError(String message) {
        Log.e(TAG, message);
    }


}
