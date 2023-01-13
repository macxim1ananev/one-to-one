package com.example.onetoone.core.feedback.interactors;

import com.example.onetoone.core.feedback.FeedbackMapper;
import com.example.onetoone.core.feedback.UserAnswerMapper;
import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateFeedbackInteractor implements Interactor<CreateFeedbackCommand, FeedbackResult> {
    private final Feedbacks feedbacks;
    private final FeedbackMapper mapper;
    private final Users users;
    private final OneToOnes oneToOnes;
    private final UserAnswers userAnswers;
    private final UserAnswerMapper userAnswerMapper;
    @Override
    public FeedbackResult execute(CreateFeedbackCommand command) {
        log.info("Executing command {}", command);

        var author = users.get(command.getAuthorId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var recipient = users.get(command.getRecipientId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var oneToOne = oneToOnes.get(command.getOneToOneId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND));

        var entity = mapper.toEntity(command, author, recipient, oneToOne);
        entity.isValid();
        var feedback = getFeedback(entity);
        var entityList = saveUserAnswer(command.getQuestions(), feedback);
        return mapper.toResult(feedback, entityList);
    }

    private List<UserAnswer> saveUserAnswer(List<UserAnswer> questions, Feedback feedback) {
         return questions.stream()
                 .map(an -> userAnswerMapper.toEntity(an, feedback.getId()))
                 .peek(userAnswers::put).toList();
    }

    private Feedback getFeedback(Feedback feedback) {
        return feedbacks.put(feedback);
    }
}
