package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.OneToOneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OneToOneRepository extends JpaRepository<OneToOneModel, Long> {
    @EntityGraph(attributePaths={"technology", "initiator", "opponent", "initiator.role", "opponent.role"})
    Page<OneToOneModel> findAll(Specification<OneToOneModel> specification,
                                Pageable pageable);

    Integer countByOpponentId(Long opponentId);

    @EntityGraph(attributePaths={"technology", "initiator", "opponent", "initiator.role", "opponent.role"})
    List<OneToOneModel> findAllByInitiatorIdOrOpponentId(Long initiatorId, Long opponentId);
}
