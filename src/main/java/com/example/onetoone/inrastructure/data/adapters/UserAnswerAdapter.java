package com.example.onetoone.inrastructure.data.adapters;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.service.interfaces.UserAnswers;
import com.example.onetoone.inrastructure.data.mappers.UserAnswerModelMapper;
import com.example.onetoone.inrastructure.data.repositories.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAnswerAdapter implements UserAnswers {
    private final UserAnswerRepository repository;
    private final UserAnswerModelMapper mapper;

    @Override
    public UserAnswer put(UserAnswer userAnswer) {
        var model = mapper.toModel(userAnswer);
        return mapper.toEntity(repository.save(model));
    }
}
