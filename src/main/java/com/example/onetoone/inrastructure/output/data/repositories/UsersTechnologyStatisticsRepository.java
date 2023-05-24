package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.UserTechnologyStatisticsModel;
import com.example.onetoone.inrastructure.output.data.models.UsersStatisticsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersTechnologyStatisticsRepository extends JpaRepository<UserTechnologyStatisticsModel, Long> {
    @Query(value = "SELECT * FROM user_technology_statistics where user_technology_statistics.user_statistics_id=?1",
            nativeQuery = true)
    List<UserTechnologyStatisticsModel> findAllByUserStatisticsId(Long userStatisticsId);

    @EntityGraph(attributePaths = { "userStatistics", "technology", "userStatistics.user"})
    Page<UserTechnologyStatisticsModel> findAll(Specification<UserTechnologyStatisticsModel> specification,
                                       Pageable pageable);
}
