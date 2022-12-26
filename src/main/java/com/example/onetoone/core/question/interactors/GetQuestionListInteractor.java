package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.GetFilteredAndSortedQuestionListCommand;
import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.core.service.common.GetListInteractor;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.core.service.interfaces.Questions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetQuestionListInteractor extends GetListInteractor
        implements Interactor<GetFilteredAndSortedQuestionListCommand, ResultModelList<QuestionResult>> {

    private final Questions questions;
    private final QuestionMapper mapper;

    @Override
    public ResultModelList<QuestionResult> execute(GetFilteredAndSortedQuestionListCommand command) {
        var filter = getListFilter(command, Question.class);
            var entityList = questions.getAll(filter);
        return new ResultModelList<>(
                entityList.getTotalItems(),
                entityList.getItems().stream().map(mapper::toResult).collect(Collectors.toList()));
    }
}
