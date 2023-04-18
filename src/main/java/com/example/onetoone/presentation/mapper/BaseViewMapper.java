package com.example.onetoone.presentation.mapper;

import java.util.List;
import java.util.Set;

public interface BaseViewMapper<E, M> {

    M toView(E resultModel);

    List<M> toListView(List<E> resultModels);

    Set<M> toSetView(Set<E> resultModels);

}
