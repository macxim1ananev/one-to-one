package com.example.onetoone.inrastructure.output.data.repositories;

import com.example.onetoone.inrastructure.output.data.models.QuestionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<QuestionModel, Long> {

    @EntityGraph(attributePaths = "technology")
    List<QuestionModel> findAllByUserId(Long userId);
    @EntityGraph(attributePaths = "technology")
    Page<QuestionModel> findAll(Specification<QuestionModel> specification,
                                   Pageable pageable);

}
