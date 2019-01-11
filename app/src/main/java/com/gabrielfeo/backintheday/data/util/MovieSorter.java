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
		// TODO Sorting by popularity
	}

	private void sortByRating() {
		// TODO Sorting by rating
	}

}
