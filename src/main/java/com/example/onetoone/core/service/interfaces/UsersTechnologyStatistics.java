package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.statistics.entity.UserTechnologyStatistics;
import com.example.onetoone.core.service.common.EntityList;
import com.example.onetoone.core.service.common.ListFilter;

import java.util.List;

public interface UsersTechnologyStatistics {
    UserTechnologyStatistics save(UserTechnologyStatistics userTechnologyStatistics);

    List<UserTechnologyStatistics> getById(Long id);
    EntityList<UserTechnologyStatistics> getAll(ListFilter filter);
}
