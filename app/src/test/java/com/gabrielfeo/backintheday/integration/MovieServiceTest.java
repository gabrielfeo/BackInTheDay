package com.gabrielfeo.backintheday.integration;

import com.gabrielfeo.backintheday.altclass.LoggingMovieDb;
import com.gabrielfeo.backintheday.data.model.MoviesResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Response;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MovieServiceTest {

	private Response<MoviesResponse> response;
	private String json;

	private void setJson(String json) {
		this.json = json;
	}

	@Before
	public void setUp() throws IOException {
		response = new LoggingMovieDb(this::setJson, Level.BODY)
				.getMovieService().getMoviesOfYear(1990).execute();
	}

	@Test
	public void MovieService_ShouldGetResponse() {
		assertNotNull(response);
	}

	@Test
	public void MovieService_ShouldGetSuccessfulResponse() {
		assertTrue(response.isSuccessful());
	}

	@Test
	public void MovieService_ShouldGetValidJsonResponse() {
		JsonObject root = new JsonParser().parse(json).getAsJsonObject();
		JsonArray results = root.getAsJsonArray("results");
		JsonObject firstResult = results.get(0).getAsJsonObject();

		assertFalse(json.isEmpty());
		assertTrue(root.has("results"));
		assertTrue(firstResult.has("vote_count"));
	}

}
