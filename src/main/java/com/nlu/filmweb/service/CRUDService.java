package com.nlu.filmweb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<S, D, C> {

    List<S> getAll();

    D deleteById(Long id);

    D insert(C c);

    D update(Long id, C c);

    D getById(Long Id);

}
