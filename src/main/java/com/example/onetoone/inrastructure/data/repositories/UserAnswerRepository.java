package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.UserAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswerModel, Long> {
    List<UserAnswerModel> getAllByFeedbackId(Long feedbackId);
}
