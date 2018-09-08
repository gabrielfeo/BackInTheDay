package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.MovieDetails;
import com.gabrielfeo.backintheday.net.ApiResponseHandler;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.net.callback.SuccessCallback;
import com.gabrielfeo.backintheday.net.moviedb.MovieDb;

public class MovieDetailsViewModel extends AndroidViewModel {

	private static final String TAG = MovieDetailsViewModel.class.getSimpleName();
	private int movieId;
	private MutableLiveData<MovieDetails> movieDetails = new MutableLiveData<>();

	public MovieDetailsViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<MovieDetails> getMovieDetails(ErrorCallback errorCallback) {
		if (movieDetails.getValue() == null) {
			refreshMovieDetails(errorCallback);
		}
		return movieDetails;
	}

	private void refreshMovieDetails(ErrorCallback errorCallback) {
		SuccessCallback<MovieDetails> successCallback = movieDetails::setValue;
		String errorMessage = getApplication().getString(R.string.moviedetail_error_getting_details);
		MovieDb.getMovieService()
		       .getMovieDetails(movieId)
		       .enqueue(new ApiResponseHandler<>(successCallback, errorCallback,
		                                         errorMessage));
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
}
