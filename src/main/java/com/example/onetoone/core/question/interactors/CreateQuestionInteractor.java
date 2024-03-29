package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.CreateQuestionCommand;
import com.example.onetoone.core.question.commands.QuestionRequest;
import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.question.results.QuestionListResult;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Questions;
import com.example.onetoone.core.service.interfaces.Technologies;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateQuestionInteractor implements Interactor<CreateQuestionCommand, QuestionListResult> {
    private final Questions questions;
    private final QuestionMapper mapper;
    private final Technologies technologies;

    @Override
    public QuestionListResult execute(CreateQuestionCommand command) {
        log.info("Executing command {}", command);

        List<Question> entities = prepareData(command);
        List<Question> resultList = entities.stream().map(questions::put).collect(Collectors.toList());
        List<QuestionResult> result2s = resultList.stream().map(mapper::toResult).toList();
        return QuestionListResult.builder()
                .userId(command.getUserId())
                .questions(result2s)
                .build();
    }

    private List<Question> prepareData(CreateQuestionCommand command){
        List<Question> result = new ArrayList<>();
        for (QuestionRequest question : command.getQuestions()) {
            var technology = technologies.get(question.getTechnologyId())
                    .orElseThrow(() -> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND));
            var entity = mapper.toEntity(question, technology);
            result.add(entity);
        }
        return result;
    }
}
