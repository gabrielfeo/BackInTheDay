package com.gabrielfeo.backintheday.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.adapter.MovieAdapter;
import com.gabrielfeo.backintheday.data.viewmodel.MoviesListViewModel;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MoviesListActivity extends AppCompatActivity {

	private MoviesListViewModel viewModel;
	private ProgressBar loadingIndicator;
	private View contentRootView;
	private RecyclerView recyclerView;
	private MovieAdapter adapter = new MovieAdapter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
		setContentView(R.layout.activity_movies_list);
		findViews();
		setIsLoading(true);
		setupRecyclerView();
		getMovies();
	}

	private void findViews() {
		loadingIndicator = findViewById(R.id.movieslist_pb_loading);
		contentRootView = findViewById(R.id.movieslist_content_root);
		recyclerView = findViewById(R.id.movieslist_rv);
	}

	private void setupRecyclerView() {
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
	}

	private void setIsLoading(boolean isLoading) {
		int contentVisibility = (isLoading) ? GONE : VISIBLE;
		contentRootView.setVisibility(contentVisibility);
		int loadingVisibility = (isLoading) ? VISIBLE : GONE;
		loadingIndicator.setVisibility(loadingVisibility);
	}

	private void getMovies() {
		ErrorCallback errorCallback = message ->
				Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
		viewModel.getMovies(errorCallback)
		         .observe(this, movies -> {
			         adapter.setMovies(movies);
			         setIsLoading(false);
		         });
	}

}
