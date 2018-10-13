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
	private ImageView posterImageView;
	private TextView titleView;
	private TextView yearView;

	public MoviePosterView(Context context) {
		super(context);
		init(null);
	}

	private void init(AttributeSet attrs) {
		inflate(getContext(), R.layout.item_movie, this);
		TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.MoviePosterView);
		posterImageView = findViewById(R.id.list_item_iv_movie_poster_image);
		titleView = findViewById(R.id.list_item_tv_movie_title);
		yearView = findViewById(R.id.list_item_tv_movie_year);
		setAttributes(attributes);
		attributes.recycle();
	}

	public MoviePosterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public MoviePosterView(Context context, AttributeSet attrs, int defStyleAttrs) {
		super(context, attrs, defStyleAttrs);
		init(attrs);
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
		if (errorDrawable != null) setImage(errorDrawable);
	}

	public void setImage(Drawable drawable) {
		posterImageView.setImageDrawable(drawable);
	}

	@Override
	public void onPrepareLoad(Drawable placeHolderDrawable) {
		if (placeHolderDrawable != null) setImage(placeHolderDrawable);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setYear(String year) {
		yearView.setText(year);
	}

}
