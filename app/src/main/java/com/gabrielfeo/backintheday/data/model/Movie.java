package com.gabrielfeo.backintheday.data.model;

import android.net.Uri;

import com.gabrielfeo.backintheday.net.url.MoviePosterUrl;
import com.google.gson.annotations.SerializedName;

/**
 * Placeholder class, valid until the movieDb API is integrated with the app.
 */
public class Movie {

	private final int id;

	@SerializedName("original_title")
	private final String title;
	@SerializedName("release_date")
	private final String releaseDate;
	@SerializedName("poster_path")
	private final String posterPath;

	public Movie(int id, String title, String releaseDate, String posterPath) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.posterPath = posterPath;
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

	public Uri getPosterUrl() {
		return MoviePosterUrl.getFor(posterPath);
	}

	public int getId() {
		return id;
	}

}
