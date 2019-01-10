package com.gabrielfeo.backintheday.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.ui.listener.OnMoviePosterClickListener;
import com.gabrielfeo.backintheday.ui.widget.MoviePosterView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

	private List<Movie> movies;
	private OnMoviePosterClickListener clickListener;

	public MoviePosterAdapter(OnMoviePosterClickListener movieClickListener) {
		this.clickListener = movieClickListener;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = new MoviePosterView(parent.getContext(), null);
		return new MoviePosterViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
		if (movies == null) { return; }
		MoviePosterView currentView = holder.moviePosterView;
		Movie currentMovie = movies.get(position);
		setPoster(currentView, currentMovie);
		setInfo(currentView, currentMovie);
		setClickAction(currentView, currentMovie);
	}

	private void setPoster(MoviePosterView view, Movie movie) {
		//TODO add image uri property to Movie class
		Picasso.get()
		       .load(movie.getPosterUrl())
		       .into(view);
	}

	private void setInfo(MoviePosterView view, Movie movie) {
		view.setTitle(movie.getTitle());
		view.setYear(movie.getReleaseYear());
	}

	private void setClickAction(MoviePosterView view, Movie movie) {
		view.setOnClickListener(clickedView -> clickListener.onMoviePosterClick(view, movie.getId()));
	}

	@Override
	public int getItemCount() {
		return (movies != null) ? movies.size() : 0;
	}

	class MoviePosterViewHolder extends RecyclerView.ViewHolder {
		private final MoviePosterView moviePosterView;

		MoviePosterViewHolder(View itemView) {
			super(itemView);
			this.moviePosterView = (MoviePosterView) itemView;
		}

	}

}
