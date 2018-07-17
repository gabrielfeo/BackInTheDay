package com.gabrielfeo.backintheday.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gabrielfeo.backintheday.R;

public class MovieDetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);
		setupToolbar();
	}

	private void setupToolbar() {
		setSupportActionBar(findViewById(R.id.moviedetail_t_toolbar));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
