package com.gabrielfeo.backintheday.ui.movieslist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.gabrielfeo.backintheday.model.Movie;

import java.util.List;

public class MovieDiffCallback extends DiffUtil.Callback {

    private List<Movie> oldList;
    private List<Movie> newList;

    public MovieDiffCallback(List<Movie> oldList, List<Movie> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(@NonNull int oldItemPosition, @NonNull int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(@NonNull int oldItemPosition, @NonNull int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

}
