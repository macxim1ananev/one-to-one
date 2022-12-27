package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.technology.entities.Technology;

import java.util.Optional;

public interface Technologies {
    Technology put(Technology entity);
    Optional<Technology> get(Long id);

    EntityList<Technology> getAll(ListFilter filter);
}
