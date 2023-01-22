package com.example.onetoone.core.feedback.interactors;

import com.example.onetoone.core.feedback.FeedbackMapper;
import com.example.onetoone.core.feedback.commands.GetByOneToOneAndRecipientIdCommand;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.Feedbacks;
import com.example.onetoone.core.service.interfaces.UserAnswers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class GetByOneToOneAndRecipientIdInteractor implements Interactor<GetByOneToOneAndRecipientIdCommand, FeedbackResult> {

    private final Feedbacks feedbacks;
    private final FeedbackMapper mapper;
    private final UserAnswers userAnswers;

    @Override
    public FeedbackResult execute(GetByOneToOneAndRecipientIdCommand command) {
        log.info("Executing command {}", command);

        var feedback = feedbacks.getByOneToOneIdAndRecipientId(command.getOneToOneId(), command.getRecipientId())
                .orElseThrow(() -> new ServiceException(ServiceException.Exception.FEEDBACK_NOT_CREATE));

        List<UserAnswer> answerList = userAnswers.getAllByFeedbackId(feedback.getId());

        return mapper.toResult(feedback, answerList);
    }
}
