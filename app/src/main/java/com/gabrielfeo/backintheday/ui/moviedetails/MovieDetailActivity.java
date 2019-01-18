package com.gabrielfeo.backintheday.ui.moviedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.model.Review;
import com.gabrielfeo.backintheday.model.Trailer;
import com.gabrielfeo.backintheday.ui.moviedetails.adapter.review.ReviewAdapter;
import com.gabrielfeo.backintheday.ui.moviedetails.adapter.trailer.TrailerAdapter;
import com.gabrielfeo.backintheday.ui.widget.MoviePosterView;
import com.gabrielfeo.backintheday.util.Timeout;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

public class MovieDetailActivity extends AppCompatActivity implements TrailerAdapter.TrailersListener {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
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
    private View trailersSection;
    private RecyclerView trailersView;
    private View reviewsSection;
    private RecyclerView reviewsView;

    private TrailerAdapter trailerAdapter = new TrailerAdapter(this);
    private ReviewAdapter reviewAdapter = new ReviewAdapter();

    private final ErrorCallback snackbarErrorCallback = () -> {
        Toast.makeText(this, getString(R.string.moviedetail_error_getting_details),
                       Toast.LENGTH_SHORT).show();
        finish();
    };
    private final Timeout loadDetailsTimeout = new Timeout(2500, snackbarErrorCallback);

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
        setupTrailersView();
        setupReviewsView();
        hidePosterFooter();
        setMovieIdFromIntent();
        observeMovieDetails();
        refreshMovieDetails();
        loadDetailsTimeout.start();
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
        trailersSection = findViewById(R.id.moviedetail_g_trailers_section);
        trailersView = findViewById(R.id.moviedetail_rv_movie_trailers);
        reviewsSection = findViewById(R.id.moviedetail_g_reviews_section);
        reviewsView = findViewById(R.id.moviedetail_rv_movie_reviews);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.moviedetail_toolbar_title);
    }

    private void setupTrailersView() {
        trailersView.setAdapter(trailerAdapter);
        trailersView.setHasFixedSize(true);
        trailersView.setLayoutManager(new LinearLayoutManager(this, HORIZONTAL, false));
    }

    private void setupReviewsView() {
        reviewsView.setAdapter(reviewAdapter);
        reviewsView.setHasFixedSize(true);
        reviewsView.setLayoutManager(new LinearLayoutManager(this, HORIZONTAL, false));
    }

    private void setMovieIdFromIntent() {
        viewModel.setMovieId(getIntent().getIntExtra(EXTRA_MOVIE_KEY, -1));
    }

    private YouTubeThumbnailLoader thumbnailLoader;

    private void observeMovieDetails() {
        viewModel.getTitle().observe(this, titleView::setText);
        viewModel.getDirectors().observe(this, directorView::setText);
        viewModel.getPosterUrl().observe(this, url -> {
            setPosterImage(url);
            loadDetailsTimeout.cancel();
            supportStartPostponedEnterTransition();
        });
        viewModel.getCountries().observe(this, countriesView::setText);
        viewModel.getYear().observe(this, yearView::setText);
        viewModel.getLanguages().observe(this, languagesView::setText);
        viewModel.getDuration().observe(this, durationView::setText);
        viewModel.getRatingTitle().observe(this, ratingTitleView::setText);
        viewModel.getRating().observe(this, ratingView::setText);
        viewModel.getSinopsis().observe(this, sinopsisView::setText);
        viewModel.getTrailers().observe(this, this::setTrailers);
        viewModel.getReviews().observe(this, this::setReviews);
    }

    private void refreshMovieDetails() {
        viewModel.refreshMovieDetails();
    }

    private void setPosterImage(Uri imageUrl) {
        Picasso.get().load(imageUrl).into(posterView);
    }

    private void setTrailers(List<Trailer> trailers) {
        trailerAdapter.setTrailers(trailers);
    }

    @Override
    public void onTrailersListEmpty() {
        hide(trailersSection);
    }

    private void setReviews(List<Review> reviews) {
        reviewAdapter.setReviews(reviews);
        show(reviewsSection);
    }

    private void show(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private void hide(View view) {
        view.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!isTaskRoot()) onBackPressed();
        return true;
    }
}
