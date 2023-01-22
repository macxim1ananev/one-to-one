package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.UsersStatisticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersStatisticsRepository extends JpaRepository<UsersStatisticsModel, Long> {
    Optional<UsersStatisticsModel> findByUserId(Long userId);
}
