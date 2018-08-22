package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.net.ApiResponseHandler;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.net.callback.SuccessCallback;
import com.gabrielfeo.backintheday.net.moviedb.MovieDb;

public class MovieDetailViewModel extends AndroidViewModel {

	private static final String TAG = MovieDetailViewModel.class.getSimpleName();
	private int movieId;
	private MutableLiveData<Movie> movie = new MutableLiveData<>();

	public MovieDetailViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<Movie> getMovie(ErrorCallback errorCallback) {
		if (movie.getValue() == null) {
			refreshMovieDetails(errorCallback);
		}
		return movie;
	}

	private void refreshMovieDetails(ErrorCallback errorCallback) {
		SuccessCallback<Movie> successCallback = movie::setValue;
		String errorMessage = getApplication().getString(R.string.moviedetail_error_getting_details);
		MovieDb.getMovieService()
		       .getMovieWithId(movieId)
		       .enqueue(new ApiResponseHandler<Movie>(successCallback, errorCallback, errorMessage));
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
}
