package com.gabrielfeo.backintheday.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.gabrielfeo.backintheday.data.moviedb.ApiResponseHandler;
import com.gabrielfeo.backintheday.data.moviedb.MovieDb;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;

import java.util.List;

public class AppMovieRepository implements MovieRepository {

    private static final String TAG = AppMovieRepository.class.getSimpleName();

    private static final class InstanceHolder {
        private static AppMovieRepository INSTANCE = new AppMovieRepository();
    }

    public static AppMovieRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public LiveData<List<Movie>> getMoviesOfYear(int year) {
        final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
        MovieDb.getMovieService()
               .getMoviesOfYear(year)
               .enqueue(new ApiResponseHandler<>(response -> movies.postValue(response.getMoviesList()),
                                                 message -> Log.e(TAG, message), "Error getting movies from remote"));
        return movies;
    }

    @Override
    public LiveData<MovieDetails> getMovieDetails(int movieId) {
        final MutableLiveData<MovieDetails> movieDetails = new MutableLiveData<>();
        MovieDb.getMovieService()
               .getMovieDetails(movieId)
               .enqueue(new ApiResponseHandler<>(movieDetails::postValue,
                                                 message -> Log.e(TAG, message),
                                                 "Error getting movie details from remote"));
        return movieDetails;
    }


}
