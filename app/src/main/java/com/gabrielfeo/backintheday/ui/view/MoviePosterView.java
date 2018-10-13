package com.gabrielfeo.backintheday.ui.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielfeo.backintheday.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Arrays;
import java.util.List;

public final class MoviePosterView extends ConstraintLayout implements Target {

	private static final String TAG = MoviePosterView.class.getSimpleName();
	private ImageView posterImageView;
	private View infoBackgroundView;
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
		infoBackgroundView = findViewById(R.id.list_item_v_movie_info_background);
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
		// TODO create a styleable attr for footer background color
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

	public void fadeFooterIn(AnimatorListener listener) {
		fadeFooterTo(1f, listener);
	}

	private void fadeFooterTo(float targetAlpha, AnimatorListener listener) {
		List<Animator> animators = Arrays.asList(getAlphaAnimatorFor(infoBackgroundView, targetAlpha),
		                                         getAlphaAnimatorFor(titleView, targetAlpha),
		                                         getAlphaAnimatorFor(yearView, targetAlpha));
		AnimatorSet animations = new AnimatorSet();
		if (listener != null) animations.addListener(listener);
		animations.playTogether(animators);
		animations.start();
	}

	private ObjectAnimator getAlphaAnimatorFor(View view, float targetAlpha) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), targetAlpha);
		animator.setDuration(250);
		return animator;
	}

	public void fadeFooterOut(AnimatorListener listener) {
		fadeFooterTo(0f, listener);
	}

}
