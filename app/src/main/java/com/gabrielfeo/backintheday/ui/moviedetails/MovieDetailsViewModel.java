package com.gabrielfeo.backintheday.ui.moviedetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gabrielfeo.backintheday.data.AppMovieRepository;
import com.gabrielfeo.backintheday.data.MovieRepository;
import com.gabrielfeo.backintheday.model.MovieDetails;
import com.gabrielfeo.backintheday.model.ProductionCountry;
import com.gabrielfeo.backintheday.model.SpokenLanguage;

import java.util.Iterator;
import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String TAG = MovieDetailsViewModel.class.getSimpleName();
    private MovieRepository movieRepository = AppMovieRepository.getInstance();
    private int movieId;
    private MediatorLiveData<String> title = new MediatorLiveData<>();
    private MediatorLiveData<String> directors = new MediatorLiveData<>();
    private MediatorLiveData<Uri> posterUrl = new MediatorLiveData<>();
    private MediatorLiveData<String> countries = new MediatorLiveData<>();
    private MediatorLiveData<String> year = new MediatorLiveData<>();
    private MediatorLiveData<String> languages = new MediatorLiveData<>();
    private MediatorLiveData<String> duration = new MediatorLiveData<>();
    private MediatorLiveData<String> ratingTitle = new MediatorLiveData<>();
    private MediatorLiveData<String> rating = new MediatorLiveData<>();
    private MediatorLiveData<String> sinopsis = new MediatorLiveData<>();

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        setRatingTitle();
    }

    private void setRatingTitle() {
        this.ratingTitle.setValue("Average Rating"); //TODO Use string res
    }

    public void refreshMovieDetails() {
        LiveData<MovieDetails> newDetails = movieRepository.getMovieDetails(movieId);
        addNewDetailsSource(newDetails);
    }

    private void addNewDetailsSource(LiveData<MovieDetails> source) {
        title.addSource(source, details -> setTitle(details.getTitle()));
        directors.addSource(source, details -> setDirectors(details.getCredits().getDirectorsNames()));
        posterUrl.addSource(source, details -> setPosterUrl(details.getPosterUrl()));
        countries.addSource(source, details -> setCountries(details.getCountries()));
        year.addSource(source, details -> setYear(details.getReleaseYear()));
        languages.addSource(source, details -> setLanguages(details.getLanguages()));
        duration.addSource(source, details -> setDuration(details.getDuration()));
        rating.addSource(source, details -> setRating(details.getRating()));
        sinopsis.addSource(source, details -> setSinopsis(details.getSinopsis()));
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    private void setTitle(String title) {
        this.title.postValue(title);
    }

    private void setDirectors(List<String> directorsNames) {
        String directors = TextUtils.join(", ", directorsNames);
        this.directors.postValue(directors);
    }

    private void setCountries(List<ProductionCountry> countriesList) {
        if (countriesList.isEmpty()) return;
        StringBuilder countriesStringBuilder = new StringBuilder();
        if (countriesList.size() == 1) {
            countriesStringBuilder.append(
                    (countriesList.get(0).getAbbreviation().equals("US"))
                    ? "United States"
                    : countriesList.get(0).getName());
        } else {
            Iterator<ProductionCountry> countries = countriesList.iterator();
            countriesStringBuilder.append(countries.next().getAbbreviation());
            while (countries.hasNext()) {
                countriesStringBuilder.append("/").append(countries.next().getAbbreviation());
            }
        }
        this.countries.postValue(countriesStringBuilder.toString());
    }

    private void setYear(String year) {
        this.year.postValue(year);
    }

    private void setLanguages(List<SpokenLanguage> languagesList) {
        if (languagesList.isEmpty()) return;
        StringBuilder languagesStringBuilder = new StringBuilder();
        if (languagesList.size() == 1) {
            languagesStringBuilder.append(languagesList.get(0).getName());
        } else {
            Iterator<SpokenLanguage> languages = languagesList.iterator();
            languagesStringBuilder.append(languages.next().getAbbreviation(true));
            while (languages.hasNext()) {
                languagesStringBuilder.append(", ").append(languages.next().getAbbreviation(true));
            }
        }
        this.languages.postValue(languagesStringBuilder.toString());
    }

    private void setDuration(int duration) {
        this.duration.postValue(String.valueOf(duration) + " min");
    }

    private void setRating(double rating) {
        this.rating.postValue(String.valueOf(rating));
    }

    private void setSinopsis(String sinopsis) {
        this.sinopsis.postValue(sinopsis);
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
        this.posterUrl.postValue(posterUrl);
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
