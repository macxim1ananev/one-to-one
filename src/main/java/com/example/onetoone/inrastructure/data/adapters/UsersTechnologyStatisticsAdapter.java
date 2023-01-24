package com.example.onetoone.inrastructure.data.adapters;

import com.example.onetoone.core.feedback.entities.statistics.UserTechnologyStatistics;
import com.example.onetoone.core.service.interfaces.UsersTechnologyStatistics;
import com.example.onetoone.inrastructure.data.mappers.FullUsersStatisticsMapper;
import com.example.onetoone.inrastructure.data.repositories.UsersTechnologyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsersTechnologyStatisticsAdapter implements UsersTechnologyStatistics {
    private final UsersTechnologyStatisticsRepository repository;
    private final FullUsersStatisticsMapper mapper;

    @Override
    public UserTechnologyStatistics save(UserTechnologyStatistics statistics) {
        var model = mapper.toModel(statistics);
        return mapper.toEntity(repository.save(model));
    }

    @Override
    public List<UserTechnologyStatistics> getById(Long id) {
        return repository.findAllByUserStatisticsId(id).stream().map(mapper::toEntity).collect(Collectors.toList());
    }
}
