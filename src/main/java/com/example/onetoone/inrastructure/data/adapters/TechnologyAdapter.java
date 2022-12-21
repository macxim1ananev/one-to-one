package com.example.onetoone.inrastructure.data.adapters;

import com.example.onetoone.core.service.interfaces.Technologies;
import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.inrastructure.data.mappers.TechnologyModelMapper;
import com.example.onetoone.inrastructure.data.repositories.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TechnologyAdapter implements Technologies {
    private final TechnologyRepository repository;
    private final TechnologyModelMapper mapper;

    @Override
    public Technology put(Technology entity) {
        var model = mapper.toModel(entity);
        return mapper.toEntity(repository.save(model));
    }

    @Override
    public Optional<Technology> get(Long id) {
        return repository.findById(id).map(mapper::toEntity);
    }
}
