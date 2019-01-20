package com.gabrielfeo.backintheday.ui.movieslist;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.ui.moviedetails.MovieDetailActivity;
import com.gabrielfeo.backintheday.ui.movieslist.adapter.MoviePosterAdapter;
import com.gabrielfeo.backintheday.ui.movieslist.paging.PageKeeper;
import com.gabrielfeo.backintheday.ui.widget.MoviePosterView;
import com.gabrielfeo.backintheday.util.logging.Logger;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public final class MoviesListActivity extends AppCompatActivity {

    private MoviesListViewModel viewModel;
    private Spinner yearSelectorView;
    private ProgressBar loadingIndicator;
    private View contentRootView;
    private RecyclerView recyclerView;
    private PageKeeper pageKeeper = new PageKeeper();
    private MoviePosterAdapter adapter = new MoviePosterAdapter(getMovieClickListener());

    private MoviePosterView.OnMoviePosterClickListener getMovieClickListener() {
        return (moviePosterView, movieId) -> {
            moviePosterView.setClickable(false);
            AnimatorListener animationEndListener = new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startMovieDetailActivity(moviePosterView, movieId);
                }
            };
            moviePosterView.fadeFooterOut(animationEndListener);
        };
    }

    private void startMovieDetailActivity(MoviePosterView moviePosterView, int movieId) {
        String transitionId = getString(R.string.transition_list_to_detail);
        Intent intent = MovieDetailActivity.getNewIntent(this, movieId);
        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, moviePosterView, transitionId).toBundle();
        startActivity(intent, options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        setContentView(R.layout.activity_movies_list);
        setExitSharedElementCallback(new ReturningSharedPosterViewCallback());
        findViews();
        setupYearSelector();
        setupRecyclerView();
    }

    private void findViews() {
        yearSelectorView = findViewById(R.id.movieslist_s_year_selector);
        loadingIndicator = findViewById(R.id.movieslist_pb_loading);
        contentRootView = findViewById(R.id.movieslist_content_root);
        recyclerView = findViewById(R.id.movieslist_rv);
    }

    private void setupYearSelector() {
        Integer[] years = viewModel.getYears();
        ArrayAdapter yearAdapter = new ArrayAdapter<>(this, R.layout.textview_actionbar_year, years);
        yearAdapter.setDropDownViewResource(R.layout.item_year);
        yearSelectorView.setAdapter(yearAdapter);
        yearSelectorView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.clearMovies();
                pageKeeper.reset();
                getMovies();
            }

            @Override
            public void onNothingSelected(AdapterView<?> view) {}
        });
        setFirstYearSelection();
    }

    private void getMovies() {
        setIsLoading(true);
        viewModel.getMovies(getSelectedYear(), pageKeeper.getCurrent())
                 .observe(this, movies -> {
                     adapter.addMovies(movies);
                     setIsLoading(false);
                 });
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemsCount = layoutManager.getItemCount();
                int onScreenItemsCount = layoutManager.getChildCount();
                int outOfScreenItemsCount = layoutManager.findFirstVisibleItemPosition();
                if (onScreenItemsCount + outOfScreenItemsCount >= totalItemsCount) {
                    pageKeeper.inc();
                    Logger.debug(this, "Increasing page count to " + pageKeeper.getCurrent());
                    getMovies();
                }
            }
        });
    }

    private void setIsLoading(boolean isLoading) {
        int contentVisibility = (isLoading) ? GONE : VISIBLE;
        contentRootView.setVisibility(contentVisibility);
        int loadingIndicatorVisibility = (isLoading) ? VISIBLE : GONE;
        loadingIndicator.setVisibility(loadingIndicatorVisibility);
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreFootersOfAllViews(recyclerView.getLayoutManager());
        if (adapter.getItemCount() == 0) getMovies();
    }

    private void restoreFootersOfAllViews(LayoutManager layoutManager) {
        if (layoutManager == null) return;
        for (int position = 0; position < layoutManager.getItemCount(); position++) {
            View currentView = layoutManager.getChildAt(position);
            if (currentView instanceof MoviePosterView) {
                restoreFooterOf((MoviePosterView) currentView);
                currentView.setClickable(true);
            }
        }
    }

    private void setFirstYearSelection() {
        yearSelectorView.setSelection(viewModel.getRandomYear());
    }

    private int getSelectedYear() {
        return (int) yearSelectorView.getSelectedItem();
    }

    private void restoreFooterOf(MoviePosterView posterView) {
        if (!posterView.isFooterFullyVisible()) { posterView.fadeFooterIn(null); }
    }

    protected void showError(String message) {
        Snackbar.make(contentRootView, message, Snackbar.LENGTH_SHORT).show();
    }

    private final class ReturningSharedPosterViewCallback extends SharedElementCallback {

        @Override
        public void onSharedElementEnd(List<String> sharedElementNames,
                                       List<View> sharedElements,
                                       List<View> sharedElementSnapshots) {
            super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
            MoviePosterView posterView = findPosterView(sharedElementNames, sharedElements);
            restoreFooterOf(posterView);
        }

        private MoviePosterView findPosterView(List<String> elementNames, List<View> elements) {
            String posterViewTransitionId = getString(R.string.transition_list_to_detail);
            int posterViewIndex = elementNames.indexOf(posterViewTransitionId);
            return (MoviePosterView) elements.get(posterViewIndex);
        }

    }

}
