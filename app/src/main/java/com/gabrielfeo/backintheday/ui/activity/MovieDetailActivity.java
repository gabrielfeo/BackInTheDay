package com.gabrielfeo.backintheday.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.MovieDetails;
import com.gabrielfeo.backintheday.data.viewmodel.MovieDetailsViewModel;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

	private static final String EXTRA_MOVIE_KEY = "movie";
	private MovieDetailsViewModel viewModel;

	private CoordinatorLayout rootView;
	private TextView titleView;
	private TextView directorView;
	private ImageView posterView;
	private TextView countriesView;
	private TextView yearView;
	private TextView languagesView;
	private TextView durationView;
	private TextView ratingTitleView;
	private TextView ratingView;
	private TextView sinopsisView;
	private final ErrorCallback snackbarErrorCallback = message -> {
		Snackbar.make(rootView, "", Snackbar.LENGTH_LONG)
		        .setAction("retry", view -> refreshMovieDetails())
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
		viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
		setContentView(R.layout.activity_movie_detail);
		findViews();
		setupToolbar();
		setMovieIdFromIntent();
		observeMovieDetails();
		refreshMovieDetails();
	}

	private void findViews() {
		rootView = findViewById(R.id.moviedetail_cl_root);
		titleView = findViewById(R.id.moviedetail_tv_title);
		directorView = findViewById(R.id.moviedetail_tv_director);
		posterView = findViewById(R.id.moviedetail_iv_poster);
		countriesView = findViewById(R.id.moviedetail_tv_countries);
		yearView = findViewById(R.id.moviedetail_tv_year);
		languagesView = findViewById(R.id.moviedetail_tv_languages);
		durationView = findViewById(R.id.moviedetail_tv_duration);
		ratingTitleView = findViewById(R.id.moviedetail_tv_rating_title);
		ratingView = findViewById(R.id.moviedetail_tv_rating_value);
		sinopsisView = findViewById(R.id.moviedetail_tv_sinopsis);
	}

	private void setupToolbar() {
		setSupportActionBar(findViewById(R.id.moviedetail_t_toolbar));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void setMovieIdFromIntent() {
		viewModel.setMovieId(getIntent().getIntExtra(EXTRA_MOVIE_KEY, -1));
	}

	private void observeMovieDetails() {
		viewModel.getTitle().observe(this, titleView::setText);
		viewModel.getDirectors().observe(this, directorView::setText);
		viewModel.getPosterUrl().observe(this, this::setPosterImage);
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

	private void setPosterImage(String imageUrl) {
		Picasso.get().load(imageUrl).into(posterView);
	}

	private void fillViewsWithMovieDetails(MovieDetails details) {
		titleView.setText(details.getTitle());

		String directorsNames = TextUtils.join(", ", details.getCredits().getDirectorsNames());
		directorView.setText(directorsNames);

		Picasso.get().load(details.getPosterUrl()).into(posterView);

		String countries = TextUtils.join("/", details.getCountriesAbbreviated());
		countriesView.setText(countries);

		yearView.setText(details.getReleaseYear());

		List<String> langCodesList = details.getLanguagesAbbreviated();
		StringBuilder langCodesStringBuilder = new StringBuilder();
		for (String code : langCodesList) {
			langCodesStringBuilder.append(Character.toTitleCase(code.charAt(0)))
			                      .append(code.substring(1));
			if (langCodesList.indexOf(code) != langCodesList.size() - 1) {
				langCodesStringBuilder.append(", ");
			}
		}
		languagesView.setText(langCodesStringBuilder.toString());

		durationView.setText(String.valueOf(details.getDuration()) + " min"); //TODO Use string res

		ratingTitleView.setText("Rating"); //TODO Use string res

		ratingView.setText(String.valueOf(details.getRating())); //TODO Add rating value to model

		sinopsisView.setText(details.getSinopsis());

	}

}
