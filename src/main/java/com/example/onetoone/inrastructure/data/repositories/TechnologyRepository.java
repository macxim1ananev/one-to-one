package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.TechnologyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<TechnologyModel, Long> {
}
