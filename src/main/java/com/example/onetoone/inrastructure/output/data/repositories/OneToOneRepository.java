package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.OneToOneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OneToOneRepository extends JpaRepository<OneToOneModel, Long> {
    Page<OneToOneModel> findAll(Specification<OneToOneModel> specification,
                                Pageable pageable);

    Integer countByOpponentId(Long opponentId);

    List<OneToOneModel> findAllByInitiatorIdOrOpponentId(Long initiatorId, Long opponentId);
    List<OneToOneModel> findAllByStatusIdOrderByDateTimeAsc(Integer statusId);
}
