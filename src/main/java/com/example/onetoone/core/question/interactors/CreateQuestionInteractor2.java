package com.example.onetoone.core.question.interactors;

import com.example.onetoone.core.question.QuestionMapper;
import com.example.onetoone.core.question.commands.CreateQuestionCommand2;
import com.example.onetoone.core.question.commands.QuestionRequest;
import com.example.onetoone.core.question.entities.Question;
import com.example.onetoone.core.question.results.QuestionResult2;
import com.example.onetoone.core.question.results.QuestionListResult2;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Questions;
import com.example.onetoone.core.service.interfaces.Technologies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateQuestionInteractor2 implements Interactor<CreateQuestionCommand2, QuestionListResult2> {
    private final Questions questions;
    private final QuestionMapper mapper;
    private final Technologies technologies;

    @Override
    public QuestionListResult2 execute(CreateQuestionCommand2 command) {

        List<Question> entities = prepareData(command);
        List<Question> resultList = entities.stream().map(questions::put2).collect(Collectors.toList());
        List<QuestionResult2> result2s = resultList.stream().map(mapper::toResult2).toList();
        return QuestionListResult2.builder()
                .userId(command.getUserId())
                .questions(result2s)
                .build();
    }

    private List<Question> prepareData(CreateQuestionCommand2 command){
        List<Question> result = new ArrayList<>();
        for (QuestionRequest question : command.getQuestions()) {
            var technology = technologies.get(question.getTechnologyId())
                    .orElseThrow(() -> new ServiceException(ServiceException.Exception.TECHNOLOGY_NOT_FOUND));
            var entity = mapper.toEntity2(question, technology);
            result.add(entity);
        }
        return result;
    }
}
