package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.Video;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VideoListSerializer {

    @TypeConverter
    public static String serialize(List<Video> videos) {
        return new Gson().toJson(videos);
    }

    @TypeConverter
    public static List<Video> deserialize(String videosString) {
        Type type = new TypeToken<ArrayList<Video>>() {}.getType();
        return new Gson().fromJson(videosString, type);
    }

}
