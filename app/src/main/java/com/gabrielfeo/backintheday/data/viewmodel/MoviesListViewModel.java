package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.callback.ApiResponseHandler;
import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.data.callback.SuccessCallback;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.model.MoviesResponse;
import com.gabrielfeo.backintheday.data.service.MovieDb;

import java.util.List;

public class MoviesListViewModel extends AndroidViewModel {

	private static final String TAG = MoviesListViewModel.class.getSimpleName();
	private MutableLiveData<List<Movie>> movies = new MutableLiveData();

	public MoviesListViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<List<Movie>> getMovies(ErrorCallback errorCallback) {
		refreshMovies(errorCallback);
		return movies;
	}

	private void refreshMovies(ErrorCallback errorCallback) {
		SuccessCallback<MoviesResponse> successCallback =
				moviesResponse -> movies.setValue(moviesResponse.getMoviesList());
		String errorMessage = getApplication().getString(R.string.movieslist_error_refresh);
		MovieDb.getMovieService()
		       .getPopular()
		       .enqueue(new ApiResponseHandler<>(successCallback, errorCallback, errorMessage));
	}

}
