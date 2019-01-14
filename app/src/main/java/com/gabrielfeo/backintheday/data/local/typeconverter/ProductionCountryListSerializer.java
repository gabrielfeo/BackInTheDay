package com.gabrielfeo.backintheday.data.local.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.gabrielfeo.backintheday.model.ProductionCountry;
import com.gabrielfeo.backintheday.util.serializer.JsonSerializer;
import com.gabrielfeo.backintheday.util.serializer.TypeReference;

import java.util.List;

public class ProductionCountryListSerializer {

    @TypeConverter
    public static String serialize(List<ProductionCountry> productionCountries) {
        return new JsonSerializer<List<ProductionCountry>>().serialize(productionCountries);
    }

    @TypeConverter
    public static List<ProductionCountry> deserialize(String productionCountriesString) {
        return new JsonSerializer<List<ProductionCountry>>().deserialize(productionCountriesString,
                                                                         new TypeReference<List<ProductionCountry>>()
                                                                         {});
    }

}
