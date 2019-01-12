package com.gabrielfeo.backintheday.model;

import com.google.gson.annotations.SerializedName;

public class CrewMember {

    @SerializedName("id")
    private final int id;
    @SerializedName("job")
    private final String job;
    @SerializedName("name")
    private final String name;
    @SerializedName("profile_path")
    private final String profilePhotoPath;

    public CrewMember(int id, String job, String name, String profilePhotoPath) {
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
