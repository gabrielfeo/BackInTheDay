package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Credits {

    private static final String JOB_DIRECTOR = "Director";
    private static final String JOB_PRODUCER = "Producer";

    @JsonProperty("cast")
    private final List<CastMember> cast;
    @JsonProperty("crew")
    private final List<CrewMember> crew;

    @JsonCreator
    public Credits(
            @JsonProperty("cast") List<CastMember> cast,
            @JsonProperty("crew") List<CrewMember> crew) {
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
