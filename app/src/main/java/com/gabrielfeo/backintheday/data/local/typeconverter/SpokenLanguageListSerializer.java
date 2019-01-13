package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.SpokenLanguage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SpokenLanguageListSerializer {

    @TypeConverter
    public static String serialize(List<SpokenLanguage> spokenLanguages) {
        return new Gson().toJson(spokenLanguages);
    }

    @TypeConverter
    public static List<SpokenLanguage> deserialize(String spokenLanguagesString) {
        Type type = new TypeToken<ArrayList<SpokenLanguage>>() {}.getType();
        return new Gson().fromJson(spokenLanguagesString, type);
    }

}
