package com.gabrielfeo.backintheday.ui.moviedetails.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.model.Review;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

public final class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    @NonNull
    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View reviewView = inflater.inflate(R.layout.item_movie_review, viewGroup, false);
        return new ReviewViewHolder(reviewView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder viewHolder, int position) {
        setAuthorFrom(reviews.get(position), viewHolder);
        setContentFrom(reviews.get(position), viewHolder);
        setViewClickAction(reviews.get(position), viewHolder);
    }

    private void setAuthorFrom(Review review, ReviewViewHolder viewHolder) {
        String author = review.getAuthor();
        viewHolder.authorView.setText(author);
    }

    private void setContentFrom(Review review, ReviewViewHolder viewHolder) {
        String content = review.getContent();
        viewHolder.contentView.setText(content);
    }

    private void setViewClickAction(Review review, ReviewViewHolder viewHolder) {
        ErrorCallback errorCallback = () -> Snackbar.make(viewHolder.itemView,
                                                          R.string.moviedetail_reviews_read_more_error, LENGTH_SHORT);
        viewHolder.itemView.setOnClickListener(
                view -> sendOpenReviewIntent(view.getContext(), review.getUrl(), errorCallback));
    }

    private void sendOpenReviewIntent(Context context, String reviewUrl, ErrorCallback errorCallback) {
        Uri parsedUri = Uri.parse(reviewUrl);
        if (parsedUri != null) {
            Intent viewIntent = new Intent(Intent.ACTION_VIEW, parsedUri);
            context.startActivity(viewIntent);
        } else {
            errorCallback.onError();
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

}
