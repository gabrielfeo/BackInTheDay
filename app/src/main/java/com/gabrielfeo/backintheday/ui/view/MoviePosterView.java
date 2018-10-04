package com.gabrielfeo.backintheday.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;

public final class MoviePosterView extends ConstraintLayout {

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

	public void setImage(Drawable drawable) {
		posterImageView.setImageDrawable(drawable);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setYear(String year) {
		yearView.setText(year);
	}

	public void setImage(Bitmap bitmap) {
		posterImageView.setImageBitmap(bitmap);
	}

}
