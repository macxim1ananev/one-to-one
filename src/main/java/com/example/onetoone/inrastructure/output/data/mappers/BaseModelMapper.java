package com.example.onetoone.inrastructure.output.data.mappers;

import java.util.List;

public interface BaseModelMapper<E, M> {

    M toModel(E entity);

    E toEntity(M model);

    List<E> toEntityList(List<M> modelList);
}
