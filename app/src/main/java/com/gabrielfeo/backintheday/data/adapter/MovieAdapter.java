package com.gabrielfeo.backintheday.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

	private List<Movie> movies;

	public MovieAdapter(List<Movie> movies) {
		setMovies(movies);
	}

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
		Movie currentMovie = movies.get(position);
		holder.title.setText(currentMovie.getTitle());
		holder.year.setText(currentMovie.getReleaseYear());
	}

	@Override
	public int getItemCount() { return movies.size(); }

	class MovieViewHolder extends RecyclerView.ViewHolder {
		private final TextView title;
		private final TextView year;

		MovieViewHolder(View itemView) {
			super(itemView);
			this.title = itemView.findViewById(R.id.list_item_tv_title);
			this.year = itemView.findViewById(R.id.list_item_tv_year);
		}

	}

}
