package com.gabrielfeo.backintheday.ui.moviedetails.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielfeo.backintheday.BuildConfig;
import com.gabrielfeo.backintheday.R;
import com.gabrielfeo.backintheday.model.Trailer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public final class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private static final String TAG = TrailerAdapter.class.getSimpleName();
    private final TrailersListener trailersListener;
    private List<Trailer> trailers;
    private int loadableTrailersCount;

    public void setTrailers(List<Trailer> trailers) {
        final List<Trailer> youtubeTrailers = trailers;
        trailers.removeIf(trailer -> !trailer.getSiteName().toLowerCase().equals("youtube"));
        this.trailers = youtubeTrailers;
        loadableTrailersCount = trailers.size();
        notifyDataSetChanged();
        if (loadableTrailersCount < 1) trailersListener.onTrailersListEmpty();
        Log.d(TAG, "Trailers list changed. Size: " + trailers.size());
    }

    public interface TrailersListener {
        void onTrailersListEmpty();
    }

    public TrailerAdapter(TrailersListener trailersListener) {
        this.trailersListener = trailersListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_movie_trailer, viewGroup, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder viewHolder, int position) {
        String videoId = trailers.get(position).getVideoId();
        initializeThumbnailFrom(viewHolder, videoId);
        setupPlayButtonFrom(viewHolder, videoId);
    }

    private void initializeThumbnailFrom(TrailerViewHolder viewHolder, String videoId) {
        TrailerThumbnailListener thumbnailListener = new TrailerThumbnailListener(videoId);
        viewHolder.thumbnail.initialize(BuildConfig.YOUTUBE_API_KEY, thumbnailListener);
    }

    private void setupPlayButtonFrom(TrailerViewHolder viewHolder, String videoId) {
        viewHolder.playButton.setOnClickListener(view -> {
            Intent playVideoIntent = YouTubeIntents.createPlayVideoIntent(view.getContext(), videoId);
            view.getContext().startActivity(playVideoIntent);
        });
    }

    @Override
    public int getItemCount() {
        return (trailers != null) ? trailers.size() : 0;
    }

    private void decreaseLoadableTrailersCount() {
        loadableTrailersCount--;
        if (loadableTrailersCount < 1) trailersListener.onTrailersListEmpty();
    }

    private final class TrailerThumbnailListener
            implements YouTubeThumbnailView.OnInitializedListener, YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        private final String videoId;

        public TrailerThumbnailListener(String videoId) {
            this.videoId = videoId;
        }

        @Override
        public void onInitializationSuccess(YouTubeThumbnailView view,
                                            YouTubeThumbnailLoader loader) {

            loader.setVideo(videoId);
            Log.d(TAG, "Tried to load video with ID " + videoId);
        }

        @Override
        public void onInitializationFailure(YouTubeThumbnailView view,
                                            YouTubeInitializationResult initializationResult) {
            Log.e(TAG, "Error initializing YouTube thumbnail. Initialization result: "
                       + initializationResult.toString());
            decreaseLoadableTrailersCount();
        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView,
                                     YouTubeThumbnailLoader.ErrorReason errorReason) {
            Log.e(TAG, "Error loading video with ID " + videoId + ". Reason: " + errorReason.name());
            decreaseLoadableTrailersCount();
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) { }
    }

}
