package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.TechnologyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<TechnologyModel, Long> {

    Optional<TechnologyModel> findById(Long id);
    Page<TechnologyModel> findAll(Specification<TechnologyModel> specification,
                                Pageable pageable);
}
