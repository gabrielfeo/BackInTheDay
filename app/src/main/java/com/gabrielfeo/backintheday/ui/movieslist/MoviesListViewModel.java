package com.gabrielfeo.backintheday.ui.movieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.util.IntegerRange;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MoviesResponse;
import com.gabrielfeo.backintheday.net.ApiResponseHandler;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.net.callback.SuccessCallback;
import com.gabrielfeo.backintheday.net.moviedb.MovieDb;

import java.util.List;
import java.util.Random;

public class MoviesListViewModel extends AndroidViewModel {

    private static final String TAG = MoviesListViewModel.class.getSimpleName();
    private MutableLiveData<List<Movie>> movies = new MutableLiveData();

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
    }

    public Integer[] getYears() {
        return IntegerRange.of(1901, 2001);
    }

    public int getRandomYear() {
        int biasedRandomSelection = 60 + new Random().nextInt(40);
        return biasedRandomSelection;
    }

    public LiveData<List<Movie>> getMovies(int year, ErrorCallback errorCallback) {
        refreshMovies(year, errorCallback);
        return movies;
    }

    private void refreshMovies(int year, ErrorCallback errorCallback) {
        SuccessCallback<MoviesResponse> successCallback =
                moviesResponse -> movies.setValue(moviesResponse.getMoviesList());
        String errorMessage = getApplication().getString(R.string.movieslist_error_refresh);
        MovieDb.getMovieService()
               .getMoviesOfYear(year)
               .enqueue(new ApiResponseHandler<>(successCallback, errorCallback, errorMessage));
    }
}
