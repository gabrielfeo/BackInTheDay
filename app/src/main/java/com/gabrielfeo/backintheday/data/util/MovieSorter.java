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
				sortByPopularityDescending();
			case HIGHEST_RATED:
				sortByRatingDescending();
		}
		return movies;
	}

	private void sortByPopularityDescending() {
		movies.sort((movie1, movie2) -> {
			if (movie1.getPopularity() < movie2.getPopularity()) {
				return 1;
			} else if (movie1.getPopularity() > movie2.getPopularity()) { return -1; } else return 0;
		});
	}

	private void sortByRatingDescending() {
		movies.sort((movie1, movie2) -> {
			if (movie1.getVoteAverage() < movie2.getVoteAverage()) {
				return 1;
			} else if (movie1.getVoteAverage() > movie2.getVoteAverage()) { return -1; } else return 0;
		});
	}

	private void sortByPopularityAscending() {
		movies.sort((movie1, movie2) -> Float.compare(movie1.getPopularity(), movie2.getPopularity()));
	}

	private void sortByRatingAscending() {
		movies.sort((movie1, movie2) -> Float.compare(movie1.getVoteAverage(), movie2.getVoteAverage()));
	}

}
