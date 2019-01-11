package com.gabrielfeo.backintheday.data.util;

import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.model.Sorting;

import java.util.List;

public final class MovieSorter {

	private final List<Movie> movies;

	public MovieSorter(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Movie> sortBy(Sorting sorting) {
		switch (sorting) {
			case MOST_POPULAR:
				sortByPopularity();
			case HIGHEST_RATED:
				sortByRating();
		}
		return movies;
	}

	private void sortByPopularity() {
        movies.sort((movie1, movie2) -> Float.compare(movie1.getPopularity(), movie2.getPopularity()));
	}

	private void sortByRating() {
        movies.sort((movie1, movie2) -> Float.compare(movie1.getVoteAverage(), movie2.getVoteAverage()));
	}

}
