package com.softserve.mosquito.repo.api;

import java.io.Serializable;
import java.util.List;

public interface GenericCRUD<E extends Serializable> {
    E create(E entity);

    E read(Long id);

    E update(E entity);

    void delete(E entity);

    List<E> readAll();
}
