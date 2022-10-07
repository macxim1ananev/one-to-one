package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.CreateQuestionCommand;
import com.example.onetoone.core.question.results.QuestionResultModel;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Questions;
import com.example.onetoone.core.service.interfaces.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateQuestionInteractor implements Interactor<CreateQuestionCommand, QuestionResultModel> {

    private final Questions questions;
    private final QuestionMapper mapper;
    private final Users users;

    @Override
    public QuestionResultModel execute(CreateQuestionCommand command) {

        var user = users.get(command.getUserId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND, command.getUserId()));
        var entity = mapper.toEntity(command);
        return mapper.toResult(questions.put(entity));
    }
}
