package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.one_to_one.entities.OneToOne;

import java.util.Optional;

public interface OneToOnes {

    OneToOne put(OneToOne entity);
    Optional<OneToOne>get(long id);
}
