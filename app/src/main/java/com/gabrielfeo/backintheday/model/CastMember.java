package com.gabrielfeo.backintheday.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CastMember {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("character")
    private final String character;

    @JsonProperty("order")
    private final String order;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("profile_path")
    private final String photoPath;

    @JsonCreator
    public CastMember(
            @JsonProperty("id") String id,
            @JsonProperty("character") String character,
            @JsonProperty("order") String order,
            @JsonProperty("name") String name,
            @JsonProperty("profile_path") String photoPath) {
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
