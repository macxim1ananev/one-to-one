package com.example.onetoone.core.service.interfaces;

import com.example.onetoone.core.feedback.rating.entity.UserStatistics;

import java.util.Optional;

public interface UsersStatistics {
    Optional<UserStatistics> get(Long id);

    UserStatistics save(UserStatistics updateStatistics);
}
