package com.example.onetoone.presentation;

import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.commands.GetByOneToOneAndRecipientIdCommand;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.user.entities.Permissions;
import com.example.onetoone.presentation.mapper.FeedbackViewMapper;
import com.example.onetoone.presentation.request.CreateFeedbackRequest;
import com.example.onetoone.presentation.view.FeedbackView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/user/one-to-one/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackViewMapper mapper;
    private final CommandBus commandBus;

    @PostMapping("/create")
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.CREATE_FEEDBACK + "')")
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
    @PreAuthorize("@securityManager.hasPermission('" + Permissions.Fields.GET_FEEDBACK_BY_ONE_TO_ONE_AND_RECIPIENT_ID + "')")
    public FeedbackView getByOneToOneAndRecipientId(@PathVariable Long recipientId, @PathVariable Long oneToOneId) {
        log.info("Request to receive all feedbacks written for the user");

        return mapper.toView(commandBus.execute(GetByOneToOneAndRecipientIdCommand
                .builder()
                .oneToOneId(oneToOneId)
                .recipientId(recipientId)
                .build()));
    }
}