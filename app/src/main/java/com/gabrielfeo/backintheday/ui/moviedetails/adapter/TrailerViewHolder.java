package com.gabrielfeo.backintheday.ui.moviedetails.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.gabrielfeo.backintheday.R;
import com.google.android.youtube.player.YouTubeThumbnailView;

final class TrailerViewHolder extends RecyclerView.ViewHolder {

    final YouTubeThumbnailView thumbnail;
    final ImageView playButton;

    public TrailerViewHolder(@NonNull View itemView) {
        super(itemView);
        thumbnail = itemView.findViewById(R.id.moviedetailtrailers_ytv_thumbnail_item);
        playButton = itemView.findViewById(R.id.moviedetailtrailers_iv_play_button);
    }

}
