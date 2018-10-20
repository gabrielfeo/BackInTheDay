package com.gabrielfeo.backintheday.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.viewmodel.MovieDetailsViewModel;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.ui.view.MoviePosterView;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

	private static final String EXTRA_MOVIE_KEY = "movie";
	private MovieDetailsViewModel viewModel;

	private CoordinatorLayout rootView;
	private Toolbar toolbar;
	private TextView titleView;
	private TextView directorView;
	private MoviePosterView posterView;
	private TextView countriesView;
	private TextView yearView;
	private TextView languagesView;
	private TextView durationView;
	private TextView ratingTitleView;
	private TextView ratingView;
	private TextView sinopsisView;
	private final ErrorCallback snackbarErrorCallback = message -> {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		finish();
	};

	public static Intent getNewIntent(Context context, int movieId) {
		Intent intent = new Intent(context, MovieDetailActivity.class);
		intent.putExtra(EXTRA_MOVIE_KEY, movieId);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
		supportPostponeEnterTransition();
		setContentView(R.layout.activity_movie_detail);
		findViews();
		setupToolbar();
		hidePosterFooter();
		setMovieIdFromIntent();
		observeMovieDetails();
		refreshMovieDetails();
	}

	private void hidePosterFooter() {
		posterView.setFooterVisible(false);
	}

	private void findViews() {
		rootView = findViewById(R.id.moviedetail_cl_root);
		toolbar = findViewById(R.id.moviedetail_t_toolbar);
		titleView = findViewById(R.id.moviedetail_tv_title);
		directorView = findViewById(R.id.moviedetail_tv_director);
		posterView = findViewById(R.id.shared_mpv_movie_poster);
		countriesView = findViewById(R.id.moviedetail_tv_countries);
		yearView = findViewById(R.id.moviedetail_tv_year);
		languagesView = findViewById(R.id.moviedetail_tv_languages);
		durationView = findViewById(R.id.moviedetail_tv_duration);
		ratingTitleView = findViewById(R.id.moviedetail_tv_rating_title);
		ratingView = findViewById(R.id.moviedetail_tv_rating_value);
		sinopsisView = findViewById(R.id.moviedetail_tv_sinopsis);
	}

	private void setupToolbar() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(R.string.moviedetail_toolbar_title);
	}

	private void setMovieIdFromIntent() {
		viewModel.setMovieId(getIntent().getIntExtra(EXTRA_MOVIE_KEY, -1));
	}

	private void observeMovieDetails() {
		viewModel.getTitle().observe(this, titleView::setText);
		viewModel.getDirectors().observe(this, directorView::setText);
		viewModel.getPosterUrl().observe(this, url -> {
			setPosterImage(url);
			supportStartPostponedEnterTransition();
		});
		viewModel.getCountries().observe(this, countriesView::setText);
		viewModel.getYear().observe(this, yearView::setText);
		viewModel.getLanguages().observe(this, languagesView::setText);
		viewModel.getDuration().observe(this, durationView::setText);
		viewModel.getRatingTitle().observe(this, ratingTitleView::setText);
		viewModel.getRating().observe(this, ratingView::setText);
		viewModel.getSinopsis().observe(this, sinopsisView::setText);
	}

	private void refreshMovieDetails() {
		viewModel.refreshMovieDetails(snackbarErrorCallback);
	}

	private void setPosterImage(Uri imageUrl) {
		Picasso.get().load(imageUrl).into(posterView);
	}

}
