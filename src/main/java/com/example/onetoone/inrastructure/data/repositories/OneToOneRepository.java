package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.OneToOneModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneToOneRepository extends JpaRepository<OneToOneModel, Long> {
}
