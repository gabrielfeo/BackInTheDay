package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.SpokenLanguage;
import com.gabrielfeo.backintheday.util.serializer.JsonSerializer;
import com.gabrielfeo.backintheday.util.serializer.TypeReference;

import java.util.List;

public class SpokenLanguageListSerializer {

    @TypeConverter
    public static String serialize(List<SpokenLanguage> spokenLanguages) {
        return new JsonSerializer<List<SpokenLanguage>>().serialize(spokenLanguages);
    }


    @TypeConverter
    public static List<SpokenLanguage> deserialize(String spokenLanguagesString) {
        return new JsonSerializer<List<SpokenLanguage>>()
                .deserialize(spokenLanguagesString, new TypeReference<List<SpokenLanguage>>() {});
    }

}
