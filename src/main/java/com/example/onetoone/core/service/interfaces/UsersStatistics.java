package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.statistics.entities.UserStatistics;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;

import java.util.Optional;

public interface UsersStatistics {
    Optional<UserStatistics> get(Long id);

    UserStatistics save(UserStatistics updateStatistics);

    EntityList<UserStatistics> getAll(ListFilter filter);
}
