package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.Credits;
import com.google.gson.Gson;

public class CreditsSerializer {

    @TypeConverter
    public static String serialize(Credits credits) {
        return new Gson().toJson(credits);
    }

    @TypeConverter
    public static Credits deserialize(String creditsString) {
        return new Gson().fromJson(creditsString, Credits.class);
    }

}
