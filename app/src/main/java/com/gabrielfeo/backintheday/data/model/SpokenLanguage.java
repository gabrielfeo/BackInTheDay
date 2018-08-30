package com.gabrielfeo.backintheday.data.model;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {

	@SerializedName("iso_639_1")
	private final String abbreviation;
	@SerializedName("name")
	private final String name;

	public SpokenLanguage(String abbreviation, String name) {
		this.abbreviation = abbreviation;
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public String getName() {
		return name;
	}

}
