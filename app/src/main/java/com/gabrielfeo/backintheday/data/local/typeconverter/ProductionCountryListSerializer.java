package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.ProductionCountry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductionCountryListSerializer {

    @TypeConverter
    public static String serialize(List<ProductionCountry> productionCountries) {
        Type type = new TypeToken<List<ProductionCountry>>() {}.getType();
        return new Gson().toJson(productionCountries, type);
    }

    @TypeConverter
    public static List<ProductionCountry> deserialize(String productionCountriesString) {
        Type type = new TypeToken<List<ProductionCountry>>() {}.getType();
        return new Gson().fromJson(productionCountriesString, type);
    }

}
