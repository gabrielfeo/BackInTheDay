package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.service.MovieDb;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListViewModel extends AndroidViewModel {

	private MutableLiveData<List<Movie>> movies = new MutableLiveData();

	public MoviesListViewModel(@NonNull Application application) {
		super(application);
		movies.setValue(Collections.emptyList());
	}

	public LiveData<List<Movie>> getMovies() {
		refreshMovies();
		return movies;
	}

	private void refreshMovies() {
		MovieDb.getMovieService().getPopular().enqueue(new Callback<List<Movie>>() {
			@Override
			public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
				movies.setValue(response.body());
			}

			@Override
			public void onFailure(Call<List<Movie>> call, Throwable t) {
				throw new RuntimeException(t);
			}
		});
	}


}
