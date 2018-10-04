package com.gabrielfeo.backintheday.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public final class MoviePosterView extends ConstraintLayout implements Target {

	private static final String TAG = MoviePosterView.class.getSimpleName();
	private final ImageView posterImageView;
	private final TextView titleView;
	private final TextView yearView;

	public MoviePosterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.item_movie, this);
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MoviePosterView);
		posterImageView = findViewById(R.id.shared_iv_movie_poster); //TODO Change ID
		titleView = findViewById(R.id.list_item_tv_movie_title);
		yearView = findViewById(R.id.list_item_tv_movie_year);
		setAttributes(attributes);
	}

	private void setAttributes(TypedArray attributes) {
		Drawable posterDrawable = attributes.getDrawable(R.styleable.MoviePosterView_posterImage);
		if (posterDrawable != null) setImage(posterDrawable);
		String title = attributes.getString(R.styleable.MoviePosterView_title);
		if (title != null) setTitle(title);
		String year = attributes.getString(R.styleable.MoviePosterView_year);
		if (year != null) setYear(year);
	}

	@Override
	public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
		setImage(bitmap);
	}

	public void setImage(Bitmap bitmap) {
		posterImageView.setImageBitmap(bitmap);
	}

	@Override
	public void onBitmapFailed(Exception exception, Drawable errorDrawable) {
		Log.e(TAG, "onBitmapFailed: ", exception);
		setImage(errorDrawable);
	}

	public void setImage(Drawable drawable) {
		posterImageView.setImageDrawable(drawable);
	}

	@Override
	public void onPrepareLoad(Drawable placeHolderDrawable) {
		setImage(placeHolderDrawable);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setYear(String year) {
		yearView.setText(year);
	}

}
