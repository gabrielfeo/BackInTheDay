package com.gabrielfeo.backintheday.ui.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.adapter.MoviePosterAdapter;
import com.gabrielfeo.backintheday.data.viewmodel.MoviesListViewModel;
import com.gabrielfeo.backintheday.net.callback.ErrorCallback;
import com.gabrielfeo.backintheday.ui.listener.OnMoviePosterClickListener;
import com.gabrielfeo.backintheday.ui.view.MoviePosterView;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MoviesListActivity extends AppCompatActivity {

	private static final String TAG = MoviesListActivity.class.getSimpleName();
	private MoviesListViewModel viewModel;
	private Toolbar toolbar;
	private ProgressBar loadingIndicator;
	private View contentRootView;
	private RecyclerView recyclerView;
	private MoviePosterAdapter adapter = new MoviePosterAdapter(getMovieClickListener());

	private OnMoviePosterClickListener getMovieClickListener() {
		return (moviePosterView, movieId) -> {
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
		setIsLoading(true);
		setupToolbar();
		setupRecyclerView();
		getMovies();
	}

	private void findViews() {
		toolbar = findViewById(R.id.movieslist_t_toolbar);
		loadingIndicator = findViewById(R.id.movieslist_pb_loading);
		contentRootView = findViewById(R.id.movieslist_content_root);
		recyclerView = findViewById(R.id.movieslist_rv);
	}

	private void setupToolbar() {
		setSupportActionBar(toolbar);
		String title = getString(R.string.app_name) + "...";
		getSupportActionBar().setTitle(title);
	}

	private void setupRecyclerView() {
		GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
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
			if (currentView instanceof MoviePosterView) { restoreFooterOf((MoviePosterView) currentView); }
		}
	}

	private void getMovies() {
		//TODO This callback is delegating presentation to the ViewModel layer. Move string res to Activity.
		ErrorCallback errorCallback =
				message -> Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
		viewModel.getMovies(errorCallback)
		         .observe(this, movies -> {
			         adapter.setMovies(movies);
			         setIsLoading(false);
		         });
	}

	private void restoreFooterOf(MoviePosterView posterView) {
		if (!posterView.isFooterFullyVisible()) { posterView.fadeFooterIn(null); }
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
