package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CrewMember {

    @JsonProperty("id")
    private final int id;

    @JsonProperty("job")
    private final String job;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("profile_path")
    private final String profilePhotoPath;

    @JsonCreator
    public CrewMember(
            @JsonProperty("id") int id,
            @JsonProperty("job") String job,
            @JsonProperty("name") String name,
            @JsonProperty("profile_path") String profilePhotoPath) {
        this.id = id;
        this.job = job;
        this.name = name;
        this.profilePhotoPath = profilePhotoPath;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

}
