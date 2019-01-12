package com.gabrielfeo.backintheday.ui.movieslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.gabrielfeo.backintheday.data.AppMovieRepository;
import com.gabrielfeo.backintheday.data.MovieRepository;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.util.IntegerRange;

import java.util.List;
import java.util.Random;

public class MoviesListViewModel extends ViewModel {

    private static final String TAG = MoviesListViewModel.class.getSimpleName();
    private MovieRepository movieRepository = new AppMovieRepository();

    public Integer[] getYears() {
        return IntegerRange.of(1901, 2001);
    }

    public int getRandomYear() {
        int biasedRandomSelection = 60 + new Random().nextInt(40);
        return biasedRandomSelection;
    }

    public LiveData<List<Movie>> getMovies(int year) {
        return movieRepository.getMoviesOfYear(year);
    }

}
