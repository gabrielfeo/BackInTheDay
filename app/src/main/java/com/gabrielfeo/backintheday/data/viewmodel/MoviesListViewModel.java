package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.model.MoviesResponse;
import com.gabrielfeo.backintheday.data.service.MovieDb;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListViewModel extends AndroidViewModel {

	private static final String TAG = MoviesListViewModel.class.getSimpleName();
	private MutableLiveData<List<Movie>> movies = new MutableLiveData();

	public MoviesListViewModel(@NonNull Application application) {
		super(application);
		movies.setValue(Collections.emptyList());
	}

	public LiveData<List<Movie>> getMovies(ErrorCallback errorCallback) {
		refreshMovies(errorCallback);
		return movies;
	}

	private void refreshMovies(ErrorCallback errorCallback) {
		MovieDb.getMovieService().getPopular().enqueue(new Callback<MoviesResponse>() {
			@Override
			public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
				if (response.body() != null && response.isSuccessful()) {
					movies.setValue(response.body().getMoviesList());
				} else {
					errorCallback.onError(getErrorMessage());
				}
			}

			private String getErrorMessage() {
				return getApplication().getString(R.string.movieslist_error_refresh);
			}

			@Override
			public void onFailure(Call<MoviesResponse> call, Throwable t) {
				Log.e(TAG, "API call failed with " + call.request().toString(), t);
				errorCallback.onError(getErrorMessage());
			}
		});
	}

}
