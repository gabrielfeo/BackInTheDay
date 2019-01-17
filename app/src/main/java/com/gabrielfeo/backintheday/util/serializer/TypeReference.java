package com.gabrielfeo.backintheday.util.serializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TypeReference to enable deserialization of parametrized types. Based on Jackson's TypeReference<>.
 *
 * @param <T> the object type
 */
public abstract class TypeReference<T> {

    private final Type type;

    {
        Type superType = this.getClass().getGenericSuperclass();
        type = ((ParameterizedType) superType).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }

}
