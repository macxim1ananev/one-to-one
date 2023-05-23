package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;

import java.util.Optional;

public interface OneToOnes {

    OneToOne put(OneToOne entity);
    Optional<OneToOne>get(long id);

    EntityList<OneToOne> getAll(ListFilter filter);

    Integer getCountByUserId(Long id);

    EntityList<OneToOne> getAllUserOneToOne(Long id);

    void delete(OneToOne oneToOne);

    EntityList<OneToOne> getAllOpen(Integer statusId);

    EntityList<OneToOne> getAllUserOpenOneToOne(Long userId);
}
