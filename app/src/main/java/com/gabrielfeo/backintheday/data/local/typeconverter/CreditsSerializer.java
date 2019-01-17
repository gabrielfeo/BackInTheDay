package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.Credits;
import com.gabrielfeo.backintheday.util.serializer.JsonSerializer;
import com.gabrielfeo.backintheday.util.serializer.TypeReference;

public class CreditsSerializer {

    @TypeConverter
    public static String serialize(Credits credits) {
        return new JsonSerializer<Credits>().serialize(credits);
    }

    @TypeConverter
    public static Credits deserialize(String creditsString) {
        return new JsonSerializer<Credits>().deserialize(creditsString, new TypeReference<Credits>() {});
    }

}
