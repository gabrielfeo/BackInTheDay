package com.gabrielfeo.backintheday.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.MovieDetails;
import com.gabrielfeo.backintheday.net.ApiResponseHandler;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.net.callback.SuccessCallback;
import com.gabrielfeo.backintheday.net.moviedb.MovieDb;

import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {

	private static final String TAG = MovieDetailsViewModel.class.getSimpleName();
	private int movieId;
	private MutableLiveData<String> title = new MutableLiveData<>();
	private MutableLiveData<String> directors = new MutableLiveData<>();
	private MutableLiveData<Uri> posterUrl = new MutableLiveData<>();
	private MutableLiveData<String> countries = new MutableLiveData<>();
	private MutableLiveData<String> year = new MutableLiveData<>();
	private MutableLiveData<String> languages = new MutableLiveData<>();
	private MutableLiveData<String> duration = new MutableLiveData<>();
	private MutableLiveData<String> ratingTitle = new MutableLiveData<>();
	private MutableLiveData<String> rating = new MutableLiveData<>();
	private MutableLiveData<String> sinopsis = new MutableLiveData<>();

	public MovieDetailsViewModel(@NonNull Application application) {
		super(application);
		setRatingTitle();
	}

	private void setRatingTitle() {
		this.ratingTitle.setValue("Rating"); //TODO Use string res
	}

	public void refreshMovieDetails(ErrorCallback errorCallback) {
		SuccessCallback<MovieDetails> successCallback = this::setNewDetails;
		String errorMessage = getApplication().getString(R.string.moviedetail_error_getting_details);
		MovieDb.getMovieService()
		       .getMovieDetails(movieId)
		       .enqueue(new ApiResponseHandler<>(successCallback, errorCallback,
		                                         errorMessage));
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	private void setNewDetails(MovieDetails details) {
		setTitle(details.getTitle());
		setDirectors(details.getCredits().getDirectorsNames());
		setPosterUrl(details.getPosterUrl());
		setCountries(details.getCountriesAbbreviated());
		setYear(details.getReleaseYear());
		setLanguages(details.getLanguagesAbbreviated());
		setDuration(details.getDuration());
		setRating(details.getRating());
		setSinopsis(details.getSinopsis());
	}

	private void setTitle(String title) {
		this.title.setValue(title);
	}

	private void setDirectors(List<String> directorsNames) {
		String directors = TextUtils.join(", ", directorsNames);
		this.directors.setValue(directors);
	}

	private void setCountries(List<String> countriesList) {
		String countries = TextUtils.join("/", countriesList);
		this.countries.setValue(countries);
	}

	private void setYear(String year) {
		this.year.setValue(year);
	}

	private void setLanguages(List<String> languagesList) {
		StringBuilder languagesStringBuilder = new StringBuilder();
		for (String language : languagesList) {
			languagesStringBuilder.append(Character.toTitleCase(language.charAt(0)))
			                      .append(language.substring(1));
			if (languagesList.indexOf(language) != languagesList.size() - 1) {
				languagesStringBuilder.append(", ");
			}
		}
		this.languages.setValue(languagesStringBuilder.toString());
	}

	private void setDuration(int duration) {
		this.duration.setValue(String.valueOf(duration));
	}

	private void setRating(double rating) {
		this.rating.setValue(String.valueOf(rating));
	}

	private void setSinopsis(String sinopsis) {
		this.sinopsis.setValue(sinopsis);
	}

	public LiveData<Uri> getPosterUrl() {
		return posterUrl;
	}

	public LiveData<String> getTitle() {
		return title;
	}

	public LiveData<String> getDirectors() {
		return directors;
	}

	private void setPosterUrl(Uri posterUrl) {
		this.posterUrl.setValue(posterUrl);
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
