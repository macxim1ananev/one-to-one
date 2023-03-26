package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.UsersStatisticsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersStatisticsRepository extends JpaRepository<UsersStatisticsModel, Long> {
    Optional<UsersStatisticsModel> findByUserId(Long userId);

    Page<UsersStatisticsModel> findAll(Specification<UsersStatisticsModel> specification,
                                       Pageable pageable);
}
