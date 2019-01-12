package com.gabrielfeo.backintheday.model;

import com.google.gson.annotations.SerializedName;

public class CastMember {

    @SerializedName("id")
    private final String id;
    @SerializedName("character")
    private final String character;
    @SerializedName("order")
    private final String order;
    @SerializedName("name")
    private final String name;
    @SerializedName("profile_path")
    private final String photoPath;

    public CastMember(String id, String character, String order, String name, String photoPath) {
        this.id = id;
        this.character = character;
        this.order = order;
        this.name = name;
        this.photoPath = photoPath;
    }

    public String getId() {
        return id;
    }

    public String getCharacter() {
        return character;
    }

    public String getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

}
