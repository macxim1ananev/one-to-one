package com.example.onetoone.presentation;

import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.commands.GetByOneToOneAndRecipientIdCommand;
import com.example.onetoone.core.feedback.commands.statistics.GetUserStatisticsCommand;
import com.example.onetoone.core.feedback.commands.statistics.GetFullUserStatisticsCommand;
import com.example.onetoone.core.feedback.commands.statistics.GetUserTechnologyStatisticsCommand;
import com.example.onetoone.core.feedback.results.statistics.FullUserStatisticsResult;
import com.example.onetoone.core.feedback.results.statistics.UserTechnologyStatisticsResult;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.service.common.ResultModelList;
import com.example.onetoone.presentation.common.ListView;
import com.example.onetoone.presentation.mapper.FeedbackViewMapper;
import com.example.onetoone.presentation.mapper.UsersStatisticsViewMapper;
import com.example.onetoone.presentation.mapper.FullUsersStatisticsViewMapper;
import com.example.onetoone.presentation.request.CreateFeedbackRequest;
import com.example.onetoone.presentation.view.FeedbackView;
import com.example.onetoone.presentation.view.FullUserStatisticsView;
import com.example.onetoone.presentation.view.UserStatisticsView;
import com.example.onetoone.presentation.view.UserTechnologyStatisticsView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/v1/user/one-to-one/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackViewMapper mapper;
    private final CommandBus commandBus;
    private final UsersStatisticsViewMapper statisticsViewMapper;
    private final FullUsersStatisticsViewMapper fullStatisticsViewMapper;

    @PostMapping("/create")
    public FeedbackView create(@Valid @RequestBody CreateFeedbackRequest request) {
        log.info("Request for create feedback");

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
        log.info("Request to receive all feedbacks written for the user");

        return mapper.toView(commandBus.execute(GetByOneToOneAndRecipientIdCommand
                .builder()
                .oneToOneId(oneToOneId)
                .recipientId(recipientId)
                .build()));
    }
    @Deprecated
    @GetMapping("/{userId}/statistics")
    public UserStatisticsView getUserStatistics(@PathVariable Long userId){
        log.info("Request for get user statistics");

        return statisticsViewMapper.toView(commandBus.execute(GetUserStatisticsCommand
                .builder()
                .id(userId)
                .build()));
    }
    @Deprecated
    @GetMapping("/{userId}/full-statistics")
    public ListView<FullUserStatisticsView> getFullUserStatistics(@PathVariable Long userId){
        log.info("Request for get full user statistics");

        ResultModelList<FullUserStatisticsResult> resultList = commandBus.execute(GetFullUserStatisticsCommand
                .builder()
                .id(userId)
                .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems()
                .stream()
                .map(fullStatisticsViewMapper::toView)
                .collect(Collectors.toList()));
    }
    @Deprecated
    @GetMapping("/{userId}/technology-statistics")
    public ListView<UserTechnologyStatisticsView> getUserTechnologyStatistics(@PathVariable Long userId){
        log.info("Request for get user technology statistics");

        ResultModelList<UserTechnologyStatisticsResult> resultList = commandBus.execute(GetUserTechnologyStatisticsCommand
                        .builder()
                        .id(userId)
                        .build());

        return new ListView<>(resultList.getTotalItems(), resultList.getItems()
                .stream()
                .map(fullStatisticsViewMapper::toTechnologyStatisticsView)
                .collect(Collectors.toList()));
    }
}
