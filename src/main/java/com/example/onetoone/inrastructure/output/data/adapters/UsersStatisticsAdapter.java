package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.feedback.entities.statistics.UserStatistics;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.inrastructure.output.data.FilteringAndSortingAdapter;
import com.example.onetoone.inrastructure.output.data.mappers.UsersStatisticsMapper;
import com.example.onetoone.inrastructure.output.data.models.UsersStatisticsModel;
import com.example.onetoone.inrastructure.output.data.repositories.UsersStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersStatisticsAdapter extends FilteringAndSortingAdapter<UsersStatisticsModel> implements UsersStatistics {
    private final UsersStatisticsRepository repository;
    private final UsersStatisticsMapper mapper;

    @Override
    public Optional<UserStatistics> get(Long id) {
        return repository.findByUserId(id).map(mapper::toEntity);
    }

    @Override
    public UserStatistics save(UserStatistics statistics) {
        var model = mapper.toModel(statistics);
        return mapper.toEntity(repository.save(model));
    }

    @Override
    public EntityList<UserStatistics> getAll(ListFilter filter) {
        var page = repository.findAll(getSpecification(filter), getPageable(filter));
        return new EntityList<>(page.getTotalElements(), page.map(mapper::toEntity).getContent());
    }
}
