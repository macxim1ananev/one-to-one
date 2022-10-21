package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.UpdateQuestionCommand;
import com.example.onetoone.core.question.results.QuestionResultModel;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Questions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateQuestionInteractor implements Interactor<UpdateQuestionCommand, QuestionResultModel> {

    private final Questions questions;
    private final QuestionMapper mapper;
    @Override
    public QuestionResultModel execute(UpdateQuestionCommand command) {
        var question = questions.getById(command.getId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.QUESTION_NOT_FOUND));
        mapper.update(question,command);
        return mapper.toResult(questions.put(question));
    }
}
