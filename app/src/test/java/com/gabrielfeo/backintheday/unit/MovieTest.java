package com.gabrielfeo.backintheday.unit;

import com.gabrielfeo.backintheday.data.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class MovieTest {

	private Movie movie;
	private String title = "The Godfather";
	private String releaseDate = "1972-03-14";
	private String releaseYear = "1972";
	private String posterPath = "/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg";

	@Before
	public void setUp() {
		movie = new Movie(title, releaseDate, posterPath);
	}

	@Test
	public void Movie_ShouldGetCorrectInformation() {
		assertThat(movie.getTitle(), equalTo(title));
		assertThat(movie.getReleaseDate(), equalTo(releaseDate));
	}

	@Test
	public void Movie_ShouldGetFormattedYearFromDate() {
		assertThat(movie.getReleaseYear(), equalTo(releaseYear));
	}

}
