package com.gabrielfeo.backintheday.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;
import com.gabrielfeo.backintheday.ui.activity.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

	private List<Movie> movies;

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.item_movie, parent, false);
		return new MovieViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
		if (movies == null) { return; }
		Movie currentMovie = movies.get(position);
		setPoster(holder, currentMovie);
		setInfo(holder, currentMovie);
		setClickAction(holder, currentMovie);
	}

	private void setPoster(MovieViewHolder holder, Movie currentMovie) {
		//TODO add image uri property to Movie class
		Picasso.get()
		       .load(currentMovie.getPosterUrl())
		       .into(holder.poster);
	}

	private void setInfo(MovieViewHolder holder, Movie currentMovie) {
		holder.title.setText(currentMovie.getTitle());
		holder.year.setText(currentMovie.getReleaseYear());
	}

	private void setClickAction(MovieViewHolder holder, Movie currentMovie) {
		holder.poster.setOnClickListener(view -> openMovieDetail(holder.itemView.getContext(),
		                                                         currentMovie.getId()));
	}

	private void openMovieDetail(Context context, int movieId) {
		context.startActivity(MovieDetailActivity.getNewIntent(context, movieId));
	}

	@Override
	public int getItemCount() {
		if (movies != null) { return movies.size(); } else { return 0; }
	}

	class MovieViewHolder extends RecyclerView.ViewHolder {
		private final ImageView poster;
		private final TextView title;
		private final TextView year;

		MovieViewHolder(View itemView) {
			super(itemView);
			this.poster = itemView.findViewById(R.id.list_item_iv_movie_poster);
			this.title = itemView.findViewById(R.id.list_item_tv_movie_title);
			this.year = itemView.findViewById(R.id.list_item_tv_movie_year);
		}

	}

}
