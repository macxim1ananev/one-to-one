package com.example.onetoone.inrastructure.data.repositories;

import com.example.onetoone.inrastructure.data.models.QuestionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<QuestionModel, Long> {

    List<QuestionModel> findAllByUserId(Long userId);
    Page<QuestionModel> findAll(Specification<QuestionModel> specification,
                                   Pageable pageable);

}
