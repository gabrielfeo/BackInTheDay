package com.gabrielfeo.backintheday.ui.moviedetails.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;

final class ReviewViewHolder extends RecyclerView.ViewHolder {

    final TextView authorView;
    final TextView contentView;
    final Button readMoreButton;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        authorView = itemView.findViewById(R.id.moviedetailreviews_tv_review_author);
        contentView = itemView.findViewById(R.id.moviedetailreviews_tv_review_content);
        readMoreButton = itemView.findViewById(R.id.moviedetailreviews_b_read_more_button);
    }

}
