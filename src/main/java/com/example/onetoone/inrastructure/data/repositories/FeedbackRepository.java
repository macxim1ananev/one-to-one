package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {
}
