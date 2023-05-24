package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.FeedbackModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {
    @EntityGraph(attributePaths = {"oneToOne", "author", "recipient", "author.id", "recipient.role", "oneToOne.technology"})
    Optional<FeedbackModel> getByOneToOneIdAndRecipientId(Long oneToOneId, Long recipientId);
    @EntityGraph(attributePaths = {"oneToOne", "author", "recipient", "author.id", "recipient.role", "oneToOne.technology"})
    Optional<FeedbackModel> getByOneToOneIdAndAuthorId(long oneToOneId, long authorId);
}