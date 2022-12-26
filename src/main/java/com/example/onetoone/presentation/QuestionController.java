package com.example.onetoone.presentation;

import com.example.onetoone.core.question.commands.CreateQuestionCommand;
import com.example.onetoone.core.question.commands.GetFilteredAndSortedQuestionListCommand;
import com.example.onetoone.core.question.commands.QuestionRequest;
import com.example.onetoone.core.question.commands.UpdateQuestionCommand;
import com.example.onetoone.core.question.results.QuestionResult;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.QuestionViewMapper;
import com.example.onetoone.presentation.request.CreateQuestionRequest;
import com.example.onetoone.presentation.view.QuestionListView;
import com.example.onetoone.presentation.view.QuestionView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.onetoone.presentation.WebUtils.getCriteria;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user/{userId}/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionViewMapper mapper;
    private final CommandBus commandBus;

    @PostMapping("/create")
    public QuestionListView addListQuestions(@PathVariable("userId") Long userId, @Valid @RequestBody CreateQuestionRequest request){
        return mapper.toListView(commandBus.execute(CreateQuestionCommand
                .builder()
                .userId(userId)
                .questions(request.getQuestions())
                .build()));
    }

    @PutMapping("/{id}")
    public QuestionView update(@PathVariable Long id, @PathVariable Long userId, @Valid @RequestBody QuestionRequest request){
        return mapper.toView(commandBus.execute(UpdateQuestionCommand
                .builder()
                .id(id)
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .technologyId(request.getTechnologyId())
                .build()));
    }

    @GetMapping
    public ListView<QuestionView> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size,
                                         @RequestParam(required = false, defaultValue = "id,desc") String sort,
                                         @RequestParam(required = false, value = "search") String search){

       var searchCriteria = getCriteria(search);
       ResultModelList<QuestionResult> resultList = commandBus.execute(GetFilteredAndSortedQuestionListCommand
               .builder()
               .page(page)
               .sort(sort)
               .size(size)
               .criteria(searchCriteria)
               .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems().stream().map(mapper::toView).collect(Collectors.toList()));
    }
}
