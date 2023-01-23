package com.example.onetoone.presentation;

import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.commands.GetByOneToOneAndRecipientIdCommand;
import com.example.onetoone.core.feedback.commands.GetUserStatisticsCommand;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.presentation.mapper.FeedbackViewMapper;
import com.example.onetoone.presentation.mapper.UsersStatisticsViewMapper;
import com.example.onetoone.presentation.request.CreateFeedbackRequest;
import com.example.onetoone.presentation.view.FeedbackView;
import com.example.onetoone.presentation.view.UserStatisticsView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user/one-to-one/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackViewMapper mapper;
    private final CommandBus commandBus;
    private final UsersStatisticsViewMapper statisticsViewMapper;

    @PostMapping("/create")
    public FeedbackView create(@Valid @RequestBody CreateFeedbackRequest request) {

        return mapper.toView(commandBus.execute(CreateFeedbackCommand
                .builder()
                .oneToOneId(request.getOneToOneId())
                .authorId(request.getAuthorId())
                .recipientId(request.getRecipientId())
                .message(request.getMessage())
                .questions(request.getQuestions())
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

    @GetMapping("/{userId}")
    public UserStatisticsView getUserStatistics(@PathVariable Long userId){

        return statisticsViewMapper.toView(commandBus.execute(GetUserStatisticsCommand
                .builder()
                .id(userId)
                .build()));
    }
}
