package com.example.onetoone.presentation;

import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.commands.GetByOneToOneAndRecipientIdCommand;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.presentation.mapper.FeedbackRequestMapper;
import com.example.onetoone.presentation.mapper.FeedbackViewMapper;
import com.example.onetoone.presentation.request.CreateFeedbackRequest;
import com.example.onetoone.presentation.view.FeedbackView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/user/one-to-one/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackViewMapper mapper;
    private final FeedbackRequestMapper requestMapper;
    private final CommandBus commandBus;

    @PostMapping("/create")
    public FeedbackView create(@Valid @RequestBody CreateFeedbackRequest request) {
        List<UserAnswer> userAnswers = request.getQuestions().stream().map(requestMapper::toEntity).collect(Collectors.toList());

        return mapper.toView(commandBus.execute(CreateFeedbackCommand
                .builder()
                .oneToOneId(request.getOneToOneId())
                .authorId(request.getAuthorId())
                .recipientId(request.getRecipientId())
                .message(request.getMessage())
                .questions(userAnswers)
                .build()));
    }

    @GetMapping("/{recipientId}/{oneToOneId}")
    public FeedbackView getByOneToOneAndRecipientId(@PathVariable Long recipientId, @PathVariable Long oneToOneId) {

        return mapper.toView(commandBus.execute(GetByOneToOneAndRecipientIdCommand
                .builder()
                .oneToOneId(oneToOneId)
                .recipientId(recipientId)
                .build()));
    }
}
