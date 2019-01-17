package com.gabrielfeo.backintheday.util.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;

public final class JsonSerializer<T> implements Serializer<T> {

    private static final ObjectMapper jacksonMapper =
            new ObjectMapper().registerModule(new ParameterNamesModule());

    @Override
    public String serialize(T object) {
        try {
            return jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public T deserialize(String objectString) {
        return deserialize(objectString, new TypeReference<T>() {});
    }

    @Override
    public T deserialize(String objectString, TypeReference<T> objectType) {
        try {
            T object = jacksonMapper.readValue(objectString,
                                               TypeFactory.defaultInstance().constructType(objectType.getType()));
            return object;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
