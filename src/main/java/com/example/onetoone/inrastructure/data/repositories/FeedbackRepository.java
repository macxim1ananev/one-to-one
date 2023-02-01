package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {
    Optional<FeedbackModel> getByOneToOneIdAndRecipientId(Long oneToOneId, Long recipientId);
}