package com.gabrielfeo.backintheday.unit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.gabrielfeo.backintheday.data.viewmodel.MovieDetailsViewModel;
import com.google.gson.JsonArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsViewModelTest {

	private Application mockApplication;
	private MovieDetailsViewModel viewModel;

	@Before
	public void setUp() {
		mockApplication = mock(Application.class);
		when(mockApplication.getString(anyInt())).thenReturn("");
		viewModel = new MovieDetailsViewModel(mockApplication);
	}

	@Test
	public void textUtilsTest() {
		viewModel.setMovieId(533);
		viewModel.refreshMovieDetails(System.out::println);
		System.out.println(viewModel.getSinopsis());
	}


}
