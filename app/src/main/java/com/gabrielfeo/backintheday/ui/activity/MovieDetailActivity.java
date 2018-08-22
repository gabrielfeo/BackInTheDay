package com.gabrielfeo.backintheday.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.data.viewmodel.MovieDetailViewModel;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

	private static final String EXTRA_MOVIE_KEY = "movie";
	private MovieDetailViewModel viewModel;

	private CoordinatorLayout rootView;
	private ImageView posterView;
	private TextView titleView;

	private final ErrorCallback snackbarErrorCallback = message -> {
		Snackbar.make(rootView, "", Snackbar.LENGTH_LONG)
		        .setAction("retry", view -> observeMovie())
		        .setText(message)
		        .show();
	};

	public static Intent getNewIntent(Context context, int movieId) {
		Intent intent = new Intent(context, MovieDetailActivity.class);
		intent.putExtra(EXTRA_MOVIE_KEY, movieId);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
		setContentView(R.layout.activity_movie_detail);
		findViews();
		setupToolbar();
		setMovieIdFromIntent();
		observeMovie();
	}

	private void findViews() {
		rootView = findViewById(R.id.moviedetail_cl_root);
		titleView = findViewById(R.id.moviedetail_tv_title);
		posterView = findViewById(R.id.moviedetail_iv_poster);
	}

	private void setupToolbar() {
		setSupportActionBar(findViewById(R.id.moviedetail_t_toolbar));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void setMovieIdFromIntent() {
		viewModel.setMovieId(getIntent().getIntExtra(EXTRA_MOVIE_KEY, -1));
	}

	private void observeMovie() {
		viewModel.getMovie(snackbarErrorCallback).observe(this, this::fillViewsWithMovie);
	}

	private void fillViewsWithMovie(Movie movie) {
		titleView.setText(movie.getTitle());
		Picasso.get().load(movie.getPosterUrl()).into(posterView);
	}

}
