package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.OneToOneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneToOneRepository extends JpaRepository<OneToOneModel, Long> {
    Page<OneToOneModel> findAll(Specification<OneToOneModel> specification,
                                Pageable pageable);
}
