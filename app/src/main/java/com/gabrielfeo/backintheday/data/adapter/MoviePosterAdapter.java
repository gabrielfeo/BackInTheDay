package com.gabrielfeo.backintheday.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.ui.listener.OnMovieClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

	private List<Movie> movies;
	private OnMovieClickListener clickListener;

	public MoviePosterAdapter(OnMovieClickListener movieClickListener) {
		this.clickListener = movieClickListener;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.item_movie, parent, false);
		return new MoviePosterViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
		if (movies == null) { return; }
		Movie currentMovie = movies.get(position);
		setPoster(holder, currentMovie);
		setInfo(holder, currentMovie);
		setClickAction(holder, currentMovie);
	}

	private void setPoster(MoviePosterViewHolder holder, Movie currentMovie) {
		//TODO add image uri property to Movie class
		Picasso.get()
		       .load(currentMovie.getPosterUrl())
		       .into(holder.poster);
	}

	private void setInfo(MoviePosterViewHolder holder, Movie currentMovie) {
		holder.title.setText(currentMovie.getTitle());
		holder.year.setText(currentMovie.getReleaseYear());
	}

	private void setClickAction(MoviePosterViewHolder holder, Movie currentMovie) {
		holder.poster.setOnClickListener(
				view -> clickListener.onMovieClick(holder.itemView, currentMovie.getId()));
	}

	@Override
	public int getItemCount() {
		return (movies != null) ? movies.size() : 0;
	}

	class MoviePosterViewHolder extends RecyclerView.ViewHolder {
		private final ImageView poster;
		private final TextView title;
		private final TextView year;

		MoviePosterViewHolder(View itemView) {
			super(itemView);
			this.poster = itemView.findViewById(R.id.shared_iv_movie_poster);
			this.title = itemView.findViewById(R.id.list_item_tv_movie_title);
			this.year = itemView.findViewById(R.id.list_item_tv_movie_year);
		}

	}

}
