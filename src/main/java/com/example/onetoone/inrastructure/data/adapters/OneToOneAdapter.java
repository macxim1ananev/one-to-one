package com.example.onetoone.inrastructure.data.adapters;

import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.inrastructure.data.FilteringAndSortingAdapter;
import com.example.onetoone.inrastructure.data.mappers.OneToOneModelMapper;
import com.example.onetoone.inrastructure.data.models.OneToOneModel;
import com.example.onetoone.inrastructure.data.repositories.OneToOneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OneToOneAdapter extends FilteringAndSortingAdapter<OneToOneModel> implements OneToOnes {

    private final OneToOneRepository repository;
    private final OneToOneModelMapper mapper;

    @Override
    public OneToOne put(OneToOne entity) {
        var oneToOneModel = mapper.toModel(entity);
        return mapper.toEntity(repository.save(oneToOneModel));
    }

    @Override
    public Optional<OneToOne> get(long id) {
        return repository.findById(id).map(mapper::toEntity);
    }

    @Override
    public EntityList<OneToOne> getAll(ListFilter filter) {
        var page = repository.findAll(getSpecification(filter), getPageable(filter));
        return new EntityList<>(page.getTotalElements(), page.map(mapper::toEntity).getContent());
    }

    @Override
    public Integer getCountByUserId(Long id) {
        return repository.countByOpponentId(id);
    }
}
