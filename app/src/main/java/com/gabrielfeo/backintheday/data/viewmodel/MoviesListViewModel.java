package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.model.MoviesResponse;
import com.gabrielfeo.backintheday.data.model.Sorting;
import com.gabrielfeo.backintheday.data.util.MovieSorter;
import com.gabrielfeo.backintheday.net.ApiResponseHandler;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.net.callback.SuccessCallback;
import com.gabrielfeo.backintheday.net.moviedb.MovieDb;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class MoviesListViewModel extends AndroidViewModel {

	private static final String TAG = MoviesListViewModel.class.getSimpleName();
	private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
	private String[] presentableSortingOptions;
	private Dictionary<String, Sorting> presentableSortingOptionsDictionary = new Hashtable<>();

	public MoviesListViewModel(@NonNull Application application) {
		super(application);
		mapSortingOptionsToDictionary();
	}

	private void mapSortingOptionsToDictionary() {
		presentableSortingOptions = getApplication().getResources().getStringArray(R.array.sorting_options);
		presentableSortingOptionsDictionary.put(presentableSortingOptions[0], Sorting.MOST_POPULAR);
		presentableSortingOptionsDictionary.put(presentableSortingOptions[1], Sorting.HIGHEST_RATED);
	}

	public LiveData<List<Movie>> getMovies(String sorting, ErrorCallback errorCallback) {
		refreshMovies(sorting, errorCallback);
		return movies;
	}

	private void refreshMovies(String sorting, ErrorCallback errorCallback) {
		SuccessCallback<MoviesResponse> successCallback = moviesResponse -> {
			List<Movie> unsortedMovies = moviesResponse.getMoviesList();
			Sorting typedSorting = presentableSortingOptionsDictionary.get(sorting);
			List<Movie> sortedMovies = new MovieSorter(unsortedMovies).sortBy(typedSorting);
			movies.setValue(sortedMovies);
		};
		String errorMessage = getApplication().getString(R.string.movieslist_error_refresh);
		MovieDb.getMovieService()
		       .getTopRatedMovies()
		       .enqueue(new ApiResponseHandler<>(successCallback, errorCallback, errorMessage));
	}

	public String[] getSortingOptions() {
		return presentableSortingOptions;
	}

}
