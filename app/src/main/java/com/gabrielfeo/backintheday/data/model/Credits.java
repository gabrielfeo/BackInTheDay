package com.gabrielfeo.backintheday.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {

	@SerializedName("cast")
	private final List<CastMember> cast;
	@SerializedName("crew")
	private final List<CrewMember> crew;

	public Credits(List<CastMember> cast, List<CrewMember> crew) {
		this.cast = cast;
		this.crew = crew;
	}

	public List<CastMember> getCast() {
		return cast;
	}

	public List<CrewMember> getCrew() {
		return crew;
	}

}
