package com.gabrielfeo.backintheday.ui.movieslist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.Sorting;
import com.gabrielfeo.backintheday.ui.widget.MoviePosterView;
import com.gabrielfeo.backintheday.util.MovieSorter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    private List<Movie> movies;
    private MoviePosterView.OnMoviePosterClickListener clickListener;

    public MoviePosterAdapter(MoviePosterView.OnMoviePosterClickListener movieClickListener) {
        this.clickListener = movieClickListener;
    }

    public void clearMovies() {
        if (movies != null) movies.clear();
    }

    public void addMovies(List<Movie> listWithNewMovies) {
        if (this.movies != null && listWithNewMovies != null && !listWithNewMovies.equals(this.movies)) {
            final List<Movie> oldMoviesList = this.movies;
            final List<Movie> distinctNewMovies = filterRepeatedMovies(listWithNewMovies);
            this.movies.addAll(distinctNewMovies);
            sortMovies();
            diffMovieLists(movies, oldMoviesList);
        } else {
            setMovies(listWithNewMovies);
        }
    }

    private List<Movie> filterRepeatedMovies(List<Movie> listWithNewMovies) {
        final List<Movie> oldMoviesList = this.movies;
        listWithNewMovies.removeIf(newMovie -> oldMoviesList.contains(newMovie));
        return listWithNewMovies;
    }

    private void sortMovies() {
        this.movies = new MovieSorter(movies).sortBy(Sorting.MOST_POPULAR);
    }

    private void diffMovieLists(List<Movie> newMoviesList, List<Movie> oldMoviesList) {
        DiffUtil.Callback diffCallback = new MovieDiffCallback(oldMoviesList, newMoviesList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
        diffResult.dispatchUpdatesTo(this);
    }

    private void setMovies(List<Movie> movies) {
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
