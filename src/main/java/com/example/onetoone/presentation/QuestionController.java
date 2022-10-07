package com.example.onetoone.presentation;

import com.example.onetoone.core.question.commands.CreateQuestionCommand;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.presentation.mapper.QuestionViewMapper;
import com.example.onetoone.presentation.request.CreateQuestionRequest;
import com.example.onetoone.presentation.view.QuestionView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionViewMapper mapper;
    private final CommandBus commandBus;

    @PostMapping("/create")
    public QuestionView create(@Valid @RequestBody CreateQuestionRequest request){

        return mapper.toView(commandBus.execute(CreateQuestionCommand
                .builder()
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .userId(request.getUserId())
                .build()));
    }
}
