package com.gabrielfeo.backintheday.ui.movieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gabrielfeo.backintheday.data.AppMovieRepository;
import com.gabrielfeo.backintheday.data.MovieRepository;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.util.IntegerRange;

import java.util.List;
import java.util.Random;

public class MoviesListViewModel extends AndroidViewModel {

    private static final String TAG = MoviesListViewModel.class.getSimpleName();
    private final MovieRepository movieRepository;

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
        movieRepository = AppMovieRepository.getInstance(getApplication());
    }

    public Integer[] getYears() {
        return IntegerRange.of(1901, 2001);
    }

    public int getRandomYear() {
        int biasedRandomSelection = 60 + new Random().nextInt(40);
        return biasedRandomSelection;
    }

    public LiveData<List<Movie>> getMovies(int year, int page) {
        return movieRepository.getMoviesOfYear(year, page);
    }

}
