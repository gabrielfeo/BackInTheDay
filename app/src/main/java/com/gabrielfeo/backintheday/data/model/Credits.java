package com.gabrielfeo.backintheday.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Credits {

	private static final String JOB_DIRECTOR = "Director";
	private static final String JOB_PRODUCER = "Producer";

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

	public List<String> getDirectorsNames() {
		List<String> names = new ArrayList<>();
		for (CrewMember director : getDirectors()) names.add(director.getName());
		return names;
	}

	public List<CrewMember> getDirectors() {
		List<CrewMember> directors = new ArrayList<>();
		for (CrewMember member : crew) {
			if (member.getJob().equals(JOB_DIRECTOR)) { directors.add(member); }
		}
		return directors;
	}

	public List<CrewMember> getProducers() {
		List<CrewMember> producers = new ArrayList<>();
		for (CrewMember member : crew) {
			if (member.getJob().equals(JOB_PRODUCER)) { producers.add(member); }
		}
		return producers;
	}

}
