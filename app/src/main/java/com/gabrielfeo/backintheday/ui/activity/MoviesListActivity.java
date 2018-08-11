package com.gabrielfeo.backintheday.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.adapter.MovieAdapter;
import com.gabrielfeo.backintheday.data.callback.UiErrorCallback;
import com.gabrielfeo.backintheday.data.viewmodel.MoviesListViewModel;

public class MoviesListActivity extends AppCompatActivity {

	private MoviesListViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movies_list);
		viewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
		setupRecyclerView();
	}

	private void setupRecyclerView() {
		RecyclerView recyclerView = findViewById(R.id.list_rv_movies_list);
		recyclerView.setHasFixedSize(true);
		setupAdapter(recyclerView);
	}

	private void setupAdapter(RecyclerView recyclerView) {
		MovieAdapter adapter = new MovieAdapter();
		recyclerView.setAdapter(adapter);
		UiErrorCallback errorCallback = message ->
				Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
		viewModel.getMovies(errorCallback).observe(this, adapter::setMovies);
	}
}