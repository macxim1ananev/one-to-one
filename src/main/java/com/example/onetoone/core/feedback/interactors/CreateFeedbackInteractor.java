package com.example.onetoone.core.feedback.interactors;

import com.example.onetoone.core.feedback.FeedbackMapper;
import com.example.onetoone.core.feedback.UserAnswerMapper;
import com.example.onetoone.core.feedback.commands.CreateFeedbackCommand;
import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.core.feedback.entities.UserAnswer;
import com.example.onetoone.core.feedback.rating.entity.UserStatistics;
import com.example.onetoone.core.feedback.rating.entity.UserTechnologyStatistics;
import com.example.onetoone.core.feedback.results.FeedbackResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.*;
import com.example.onetoone.core.user.entities.User;
import com.example.onetoone.presentation.request.UserAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateFeedbackInteractor implements Interactor<CreateFeedbackCommand, FeedbackResult> {
    private final Feedbacks feedbacks;
    private final FeedbackMapper mapper;
    private final Users users;
    private final OneToOnes oneToOnes;
    private final UserAnswers userAnswers;
    private final UsersStatistics usersStatistics;
    private final UsersTechnologyStatistics usersTechnologyStatistics;
    private final Technologies technologies;
    private final UserAnswerMapper userAnswerMapper;

    @Override
    public FeedbackResult execute(CreateFeedbackCommand command) {
        var author = users.get(command.getAuthorId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var recipient = users.get(command.getRecipientId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var oneToOne = oneToOnes.get(command.getOneToOneId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND));

        var entity = mapper.toEntity(command, author, recipient, oneToOne);
        entity.isValid();
        var feedback = getFeedback(entity);
        var answers = toUserAnswer(command.getQuestions());
        var entityList = saveUserAnswer(answers, feedback);

        var userStatistics = saveStatistics(updateStatistics(recipient, entityList));
        saveUserTechnologyStatistics(updateUserTechnologyStatistics(recipient, entityList, userStatistics));
        return mapper.toResult(feedback, entityList);
    }

    private void saveUserTechnologyStatistics(List<UserTechnologyStatistics> statistics) {
        statistics.forEach(usersTechnologyStatistics::save);
    }

    private UserStatistics saveStatistics(UserStatistics statistics) {
        return usersStatistics.save(statistics);

    }

    private UserStatistics updateStatistics(User user, List<UserAnswer> userAnswers) {
        var userStatistics = usersStatistics.get(user.getId());

        if (userStatistics.isEmpty()) {
            var newUserStatistics = new UserStatistics();
            newUserStatistics.setUser(user);
            newUserStatistics.setTotalOneToOneCount(1);
            newUserStatistics.setTotalQuestionCount(userAnswers.size());
            newUserStatistics.setTotalPoint(getTotalPoint(userAnswers));
            return newUserStatistics;
        } else {
            var currentUserStatistics = userStatistics.get();
            currentUserStatistics.incrementOneToOneCount();
            currentUserStatistics.incrementTotalQuestionCount(userAnswers.size());
            currentUserStatistics.plusTotalPoint(getTotalPoint(userAnswers));
            return currentUserStatistics;
        }
    }

    private Integer getTotalPoint(List<UserAnswer> userAnswers){
        Integer totalPoint = 0;
        for (UserAnswer ua : userAnswers){
            totalPoint+=ua.getResponseLevel();
        }
        return totalPoint;
    }

    private List<UserTechnologyStatistics> updateUserTechnologyStatistics(User recipient, List<UserAnswer> userAnswers, UserStatistics userStatistics) {
        var statistics = usersTechnologyStatistics.getById(recipient.getId());

        if (statistics.isEmpty()) {
            List<UserTechnologyStatistics> newStatistics = new ArrayList<>();
            for (UserAnswer ua : userAnswers) {
                var technologyId = ua.getQuestion().getTechnology().getId();
                var uts = new UserTechnologyStatistics();
                uts.setUserStatistics(userStatistics);
                uts.setTechnologyId(technologyId);
                uts.incrementQuestionCount();
                uts.plusTotalPoint(ua.getResponseLevel());
                newStatistics.add(uts);
            }
            return newStatistics;
        } else {
            UA:
            for (UserAnswer ua : userAnswers) {
                var technologyId = ua.getQuestion().getTechnology().getId();
                UTS:
                for (UserTechnologyStatistics uts : statistics) {
                    if (technologyId == uts.getTechnologyId()) {
                        uts.incrementQuestionCount();
                        uts.plusTotalPoint(ua.getResponseLevel());
                        break UTS;
                    }
                }

                var newUts = new UserTechnologyStatistics();
                newUts.setUserStatistics(userStatistics);
                newUts.setQuestionCount(1);
                newUts.setTechnologyId(ua.getQuestion().getTechnology().getId());
                newUts.setTotalPoint(ua.getResponseLevel());
                statistics.add(newUts);
            }
        }
        return statistics;
    }

    private List<UserAnswer> toUserAnswer(List<UserAnswerRequest> questions) {
        List<UserAnswer> resultLis = new ArrayList<>();
        for (UserAnswerRequest usr : questions) {
            var technology = technologies.get(usr.getQuestion().getTechnologyId()).get();
            var ua = userAnswerMapper.toUserAnswer(usr);
            ua.getQuestion().setTechnology(technology);
            resultLis.add(ua);
        }
        return resultLis;
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
