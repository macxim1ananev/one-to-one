package com.example.onetoone.inrastructure.input.data.adapters;

import com.example.onetoone.core.feedback.entities.statistics.UserStatistics;
import com.example.onetoone.core.service.interfaces.UsersStatistics;
import com.example.onetoone.inrastructure.input.data.mappers.UsersStatisticsMapper;
import com.example.onetoone.inrastructure.input.data.repositories.UsersStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersStatisticsAdapter implements UsersStatistics {
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
}
