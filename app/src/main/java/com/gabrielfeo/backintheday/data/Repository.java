package com.gabrielfeo.backintheday.data;

import java.util.List;

public interface Repository<T> {

    public List<T> getAll();
    public T getById(String id);

}
