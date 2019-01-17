package com.gabrielfeo.backintheday.util.serializer;


/**
 * Serializer to abstract JSON library calls.
 *
 * @param <T> Java object type
 */
public interface Serializer<T> {

    String serialize(T object);
    T deserialize(String objectString);
    T deserialize(String objectString, TypeReference<T> objectType);

}

// TODO Research how to abstract Jackson annotations