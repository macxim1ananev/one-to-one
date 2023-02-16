package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.service.interfaces.UserAnswers;
import com.example.onetoone.inrastructure.output.data.mappers.UserAnswerModelMapper;
import com.example.onetoone.inrastructure.output.data.repositories.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserAnswer> getAllByFeedbackId(Long feedbackId) {
        var modelList = repository.getAllByFeedbackId(feedbackId);
        return modelList.stream().map(mapper::toEntity).collect(Collectors.toList());
    }
}
