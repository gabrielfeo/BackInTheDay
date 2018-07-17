package com.gabrielfeo.backintheday.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

	private Movie[] movies;

	public MovieAdapter(Movie[] movies) {
		this.movies = movies;
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
		holder.title.setText(movies[position].getTitle());
		String year = String.valueOf(movies[position].getYear());
		holder.year.setText(year);
	}

	@Override
	public int getItemCount() { return movies.length; }

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
