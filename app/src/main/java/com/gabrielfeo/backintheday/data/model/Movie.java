package com.gabrielfeo.backintheday.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Placeholder class, valid until the movieDb API is integrated with the app.
 */
public class Movie {

	@SerializedName("original_title")
	private final String title;

	@SerializedName("release_date")
	private final String releaseDate;

	public Movie(String title, String releaseDate) {
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public String getReleaseYear() {
		return getReleaseDate().substring(0, 4);
	}

	public String getReleaseDate() {
		return releaseDate;
	}
}
