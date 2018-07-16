package com.gabrielfeo.backintheday;

/**
 * Placeholder class, valid until the movieDb API is integrated with the app.
 */
public class Movie {

	private final String title;
	private final int year;

	public Movie(String title, int year) {
		this.title = title;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

}
