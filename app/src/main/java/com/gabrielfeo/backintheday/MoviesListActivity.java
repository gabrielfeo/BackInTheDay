package com.gabrielfeo.backintheday;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

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
		recyclerView.setAdapter(new MovieAdapter(viewModel.getMovies()));
	}
}
