package com.gabrielfeo.backintheday;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

public class MoviesListViewModel extends AndroidViewModel {

	private Movie[] movies = {
			new Movie("The Godfather", 1972),
			new Movie("The Godfather Part II", 1975)};

	public MoviesListViewModel(@NonNull Application application) {
		super(application);
	}

	public Movie[] getMovies() {
		return movies;
	}


}
