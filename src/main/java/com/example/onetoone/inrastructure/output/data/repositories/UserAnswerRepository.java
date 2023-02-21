package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.UserAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswerModel, Long> {
    List<UserAnswerModel> getAllByFeedbackId(Long feedbackId);
}
