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
import com.gabrielfeo.backintheday.model.Trailer;

import java.util.Iterator;
import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String TAG = MovieDetailsViewModel.class.getSimpleName();
    private MovieRepository movieRepository = AppMovieRepository.getInstance(getApplication());
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
    private MediatorLiveData<List<Trailer>> trailers = new MediatorLiveData<>();

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
        LiveData<List<Trailer>> newTrailers = movieRepository.getTrailersByMovieId(movieId);
        addNewTrailersSource(newTrailers);
    }

    private void addNewDetailsSource(LiveData<MovieDetails> source) {
        title.addSource(source, movieDetails -> setTitleFrom(movieDetails));
        directors.addSource(source, movieDetails -> setDirectorsFrom(movieDetails));
        posterUrl.addSource(source, movieDetails -> setPosterUrlFrom(movieDetails));
        countries.addSource(source, movieDetails -> setCountriesFrom(movieDetails));
        year.addSource(source, movieDetails -> setYearFrom(movieDetails));
        languages.addSource(source, movieDetails -> setLanguagesFrom(movieDetails));
        duration.addSource(source, movieDetails -> setDurationFrom(movieDetails));
        rating.addSource(source, movieDetails -> setRatingFrom(movieDetails));
        sinopsis.addSource(source, movieDetails -> setSinopsisFrom(movieDetails));
    }

    private void addNewTrailersSource(LiveData<List<Trailer>> source) {
        trailers.addSource(source, movieTrailers -> setTrailersFrom(movieTrailers));
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    private void setTitleFrom(MovieDetails details) {
        if (details != null && details.getTitle() != null)
            this.title.postValue(details.getTitle());
    }

    private void setDirectorsFrom(MovieDetails details) {
        if (details != null && details.getCredits() != null && details.getCredits().getDirectorsNames() != null) {
            List<String> directorsNames = details.getCredits().getDirectorsNames();
            String directors = TextUtils.join(", ", directorsNames);
            this.directors.postValue(directors);
        }
    }

    private void setCountriesFrom(MovieDetails details) {
        if (details != null && details.getCountries() != null && !details.getCountries().isEmpty()) {
            List<ProductionCountry> countriesList = details.getCountries();
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
    }

    private void setYearFrom(MovieDetails details) {
        if (details != null && details.getReleaseYear() != null) {
            String year = details.getReleaseYear();
            this.year.postValue(year);
        }
    }

    private void setLanguagesFrom(MovieDetails details) {
        if (details != null && details.getLanguages() != null) {
            List<SpokenLanguage> languagesList = details.getLanguages();
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
    }

    private void setDurationFrom(MovieDetails details) {
        if (details != null && details.getDuration() > 0)
            this.duration.postValue(String.valueOf(details.getDuration()) + " min");
    }

    private void setRatingFrom(MovieDetails details) {
        if (details != null && details.getRating() > 0)
            this.rating.postValue(String.valueOf(details.getRating()));
    }

    private void setSinopsisFrom(MovieDetails details) {
        if (details != null && details.getSinopsis() != null)
            this.sinopsis.postValue(details.getSinopsis());
    }

    private void setPosterUrlFrom(MovieDetails details) {
        if (details != null && details.getPosterUrl() != null)
            this.posterUrl.postValue(details.getPosterUrl());
    }

    private void setTrailersFrom(List<Trailer> trailers) {
        if (trailers != null && trailers.size() > 0)
            this.trailers.postValue(trailers);
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

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

}
