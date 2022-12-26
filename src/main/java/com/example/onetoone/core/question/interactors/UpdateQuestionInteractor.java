package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.UpdateQuestionCommand;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Questions;
import com.example.onetoone.core.service.interfaces.Technologies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateQuestionInteractor implements Interactor<UpdateQuestionCommand, QuestionResult> {

    private final Questions questions;
    private final QuestionMapper mapper;
    private final Technologies technologies;
    @Override
    public QuestionResult execute(UpdateQuestionCommand command) {
        var technology = technologies.get(command.getTechnologyId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND));
        var question = questions.getById(command.getId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.QUESTION_NOT_FOUND));

        question.setQuestion(command.getQuestion());
        question.setAnswer(command.getAnswer());
        question.setTechnology(technology);
        return mapper.toResult(questions.put(question));
    }
}
