package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.UsersStatisticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersStatisticsRepository extends JpaRepository<UsersStatisticsModel, Long> {
}
