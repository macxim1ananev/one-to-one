package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.feedback.entities.statistics.UserTechnologyStatistics;
import com.example.onetoone.core.service.interfaces.UsersTechnologyStatistics;
import com.example.onetoone.inrastructure.output.data.mappers.FullUsersStatisticsMapper;
import com.example.onetoone.inrastructure.output.data.repositories.UsersTechnologyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Component
@RequiredArgsConstructor
public class UsersTechnologyStatisticsAdapter implements UsersTechnologyStatistics {
    private final UsersTechnologyStatisticsRepository repository;
    private final FullUsersStatisticsMapper mapper;

    @Override
    public UserTechnologyStatistics save(UserTechnologyStatistics statistics) {
        log.info("Request for save user technology statistics");
        var model = mapper.toModel(statistics);
        return mapper.toEntity(repository.save(model));
    }

    @Override
    public List<UserTechnologyStatistics> getById(Long id) {
        return repository.findAllByUserStatisticsId(id).stream().map(mapper::toEntity).collect(Collectors.toList());
    }
}
