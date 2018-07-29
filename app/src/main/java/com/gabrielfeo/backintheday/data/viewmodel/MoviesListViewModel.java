package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gabrielfeo.backintheday.data.callback.UiErrorCallback;
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

	public LiveData<List<Movie>> getMovies(UiErrorCallback uiErrorCallback) {
		refreshMovies(uiErrorCallback);
		return movies;
	}

	private void refreshMovies(UiErrorCallback uiErrorCallback) {
		MovieDb.getMovieService().getPopular().enqueue(new Callback<MoviesResponse>() {
			@Override
			public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
				if (response.body() != null) { movies.setValue(response.body().getMoviesList()); }
			}

			@Override
			public void onFailure(Call<MoviesResponse> call, Throwable t) {
				Log.e(TAG, "API call failed with " + call.request().toString(), t);
				uiErrorCallback.onError("Failed to refresh movies");
			}
		});
	}

}
