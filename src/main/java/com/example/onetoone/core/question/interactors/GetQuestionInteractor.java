package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.GetQuestionCommand;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Questions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class GetQuestionInteractor implements Interactor<GetQuestionCommand, QuestionResult> {

    private final Questions questions;
    private final QuestionMapper mapper;

    @Override
    public QuestionResult execute(GetQuestionCommand command) {
        log.info("Executing command {}", command);

        var entity = questions.getById(command.getId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.QUESTION_NOT_FOUND, command.getId()));

        return mapper.toResult(entity);
    }
}
