package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import com.example.onetoone.inrastructure.output.data.FilteringAndSortingAdapter;
import com.example.onetoone.inrastructure.output.data.mappers.OneToOneModelMapper;
import com.example.onetoone.inrastructure.output.data.models.OneToOneModel;
import com.example.onetoone.inrastructure.output.data.repositories.OneToOneRepository;
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
    public void delete(OneToOne oneToOne) {
        repository.delete(mapper.toModel(oneToOne));
    }

    @Override
    public EntityList<OneToOne> getAllUserOneToOne(Long id) {
        var list = repository.findAllByInitiatorIdOrOpponentId(id, id);
        return new EntityList<>(list.size(), list.stream().map(mapper::toEntity).toList());
    }

    @Override
    public Integer getCountByUserId(Long id) {
        return repository.countByOpponentId(id);
    }

    @Override
    public EntityList<OneToOne> getAllOpen(Integer statusId) {
        var list = repository.findAllByStatusIdOrderByDateTimeAsc(statusId);
        return new EntityList<>(list.size(), list.stream().map(mapper::toEntity).toList());
    }

    @Override
    public EntityList<OneToOne> getAllUserOpenOneToOne(Long userId) {
        var list = repository.findAllByInitiatorIdAndStatusId(userId, OneToOneStatus.OPEN.getId());
        return new EntityList<>(list.size(), list.stream().map(mapper::toEntity).toList());
    }
}
