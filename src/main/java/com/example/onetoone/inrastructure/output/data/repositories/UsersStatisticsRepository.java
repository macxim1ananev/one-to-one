package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.UsersStatisticsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersStatisticsRepository extends JpaRepository<UsersStatisticsModel, Long> {
    @EntityGraph(attributePaths = "user")
    Optional<UsersStatisticsModel> findByUserId(Long userId);

    @EntityGraph(attributePaths = "user")
    Page<UsersStatisticsModel> findAll(Specification<UsersStatisticsModel> specification,
                                       Pageable pageable);
}
