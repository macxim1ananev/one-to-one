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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    private final UserAnswerMapper userAnswerMapper;
    @Override
    public FeedbackResult execute(CreateFeedbackCommand command) {
        var author = users.get(command.getAuthorId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var recipient = users.get(command.getRecipientId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_NOT_FOUND));
        var oneToOne = oneToOnes.get(command.getOneToOneId()).orElseThrow(() -> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND));

        var entity = mapper.toEntity(command, author, recipient, oneToOne);
        entity.isValid();
        var feedback = getFeedback(entity);
        var entityList = saveUserAnswer(command.getQuestions(), feedback);
        //update statistics
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

    private UserStatistics updateStatistics(User user, List<UserAnswer> userAnswers){
        UserStatistics userStatistics = new UserStatistics();

        if (userStatistics==null){
            userStatistics.setTotalOneToOneCount(1);
            userStatistics.setTotalQuestionCount(userAnswers.size());
            userStatistics.setTechnologyStatistics(updateTechnologyStatistics(userAnswers, userStatistics));
        } else {
            userStatistics.incrementOneToOneCount();
            userStatistics.incrementTotalQuestionCount(userAnswers.size());
            userStatistics.setTechnologyStatistics(updateTechnologyStatistics(userAnswers, userStatistics));
        }
        return userStatistics;
    }

    private List<UserTechnologyStatistics> updateTechnologyStatistics(List<UserAnswer> userAnswers,
                                                                      UserStatistics userStatistics) {

        var userTechnologyStatistics = userStatistics.getTechnologyStatistics();
        var totalPoint = userStatistics.getTotalPoint();

        UA: for (UserAnswer ua : userAnswers) {
            var technologyId = ua.getQuestion().getTechnology().getId();
            totalPoint += ua.getResponseLevel();
          UTS:  for (UserTechnologyStatistics uts: userTechnologyStatistics) {
                if (technologyId == uts.getTechnologyId()){
                    uts.incrementQuestionCount();
                    uts.plusTotalPoint(ua.getResponseLevel());
                    break UTS;
                }
            }

           var newUts = new UserTechnologyStatistics();
           newUts.setQuestionCount(1);
           newUts.setTechnologyId(ua.getQuestion().getTechnology().getId());
           newUts.setTotalPoint(ua.getResponseLevel());
           userTechnologyStatistics.add(newUts);
        }
        userStatistics.plusTotalPoint(totalPoint);
        return userTechnologyStatistics;
    }
}
