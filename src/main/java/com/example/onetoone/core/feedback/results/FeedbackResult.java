package com.example.onetoone.core.feedback.results;

import com.example.onetoone.core.feedback.commands.UserAnswerRequestModel;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.user.results.UserResult;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FeedbackResult {
    Long id;
    OneToOneResult oneToOne;
    UserResult author;
    UserResult recipient;
    List<UserAnswerRequestModel> userAnswerResults;
    String message;
}
