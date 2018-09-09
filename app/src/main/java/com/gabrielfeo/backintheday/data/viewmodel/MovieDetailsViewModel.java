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
	private MutableLiveData<String> title = new MutableLiveData<>();
	private MutableLiveData<String> directors = new MutableLiveData<>();
	private MutableLiveData<String> posterUrl = new MutableLiveData<>();
	private MutableLiveData<String> countries = new MutableLiveData<>();
	private MutableLiveData<String> year = new MutableLiveData<>();
	private MutableLiveData<String> languages = new MutableLiveData<>();
	private MutableLiveData<String> duration = new MutableLiveData<>();
	private MutableLiveData<String> ratingTitle = new MutableLiveData<>();
	private MutableLiveData<String> rating = new MutableLiveData<>();
	private MutableLiveData<String> sinopsis = new MutableLiveData<>();

	public MovieDetailsViewModel(@NonNull Application application) {
		super(application);
		ratingTitle.setValue("Rating"); //TODO Use string res
	}

	public void refreshMovieDetails(ErrorCallback errorCallback) {
		SuccessCallback<MovieDetails> successCallback = details -> {}; //TODO Implement callback
		String errorMessage = getApplication().getString(R.string.moviedetail_error_getting_details);
		MovieDb.getMovieService()
		       .getMovieDetails(movieId)
		       .enqueue(new ApiResponseHandler<>(successCallback, errorCallback,
		                                         errorMessage));
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public LiveData<String> getTitle() {
		return title;
	}

	public LiveData<String> getDirectors() {
		return directors;
	}

	public LiveData<String> getPosterUrl() {
		return posterUrl;
	}

	public LiveData<String> getCountries() {
		return countries;
	}

	public LiveData<String> getYear() {
		return year;
	}

	public LiveData<String> getLanguages() {
		return languages;
	}

	public LiveData<String> getDuration() {
		return duration;
	}

	public LiveData<String> getRatingTitle() {
		return ratingTitle;
	}

	public LiveData<String> getRating() {
		return rating;
	}

	public LiveData<String> getSinopsis() {
		return sinopsis;
	}

}
