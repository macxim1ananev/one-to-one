package com.example.onetoone.presentation;

import com.example.onetoone.core.question.commands.CreateQuestionCommand;
import com.example.onetoone.core.question.commands.CreateQuestionCommand2;
import com.example.onetoone.core.question.commands.GetFilteredAndSortedQuestionListCommand;
import com.example.onetoone.core.question.commands.UpdateQuestionCommand;
import com.example.onetoone.core.question.results.QuestionResultModel;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.QuestionViewMapper;
import com.example.onetoone.presentation.request.CreateQuestionRequest;
import com.example.onetoone.presentation.request.CreateQuestionRequest2;
import com.example.onetoone.presentation.request.UpdateQuestionRequest;
import com.example.onetoone.presentation.view.QuestionView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionViewMapper mapper;
    private final CommandBus commandBus;

    @PostMapping("/create")
    public QuestionView addQuestion(@Valid @RequestBody CreateQuestionRequest request){

        return mapper.toView(commandBus.execute(CreateQuestionCommand
                .builder()
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .userId(request.getUserId())
                .build()));
    }

    @PostMapping("/{userId}/create-list")
    public Object addListQuestions(@PathVariable("userId") Long userId, @Valid @RequestBody CreateQuestionRequest2 request){
        return mapper.toView2(commandBus.execute(CreateQuestionCommand2
                .builder()
                .userId(userId)
                .questions(request.getQuestions())
                .build()));
    }

    @PutMapping("/{id}")
    public QuestionView update(@PathVariable Long id, @Valid @RequestBody UpdateQuestionRequest request){
        return mapper.toView(commandBus.execute(UpdateQuestionCommand
                .builder()
                .id(id)
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .userId(request.getUserId())
                .build()));
    }

    @GetMapping
    public ListView<QuestionView> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size,
                                         @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                         @RequestParam(required = false, value = "search") String search){

       var searchCriteria = getCriteria(search);
       ResultModelList<QuestionResultModel> resultList = commandBus.execute(GetFilteredAndSortedQuestionListCommand
               .builder()
               .page(page)
               .sort(sort)
               .size(size)
               .criteria(searchCriteria)
               .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems().stream().map(mapper::toView).collect(Collectors.toList()));
    }
}
