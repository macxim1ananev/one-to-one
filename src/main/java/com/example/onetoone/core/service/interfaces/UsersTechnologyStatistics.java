package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.feedback.rating.entity.UserTechnologyStatistics;

import java.util.List;

public interface UsersTechnologyStatistics {
    UserTechnologyStatistics save(UserTechnologyStatistics userTechnologyStatistics);

    List<UserTechnologyStatistics> getById(Long id);
}
