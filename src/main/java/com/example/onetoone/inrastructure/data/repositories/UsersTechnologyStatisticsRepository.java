package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.UserTechnologyStatisticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTechnologyStatisticsRepository extends JpaRepository<UserTechnologyStatisticsModel, Long> {
}
