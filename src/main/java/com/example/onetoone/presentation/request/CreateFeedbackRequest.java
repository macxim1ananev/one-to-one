package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateFeedbackRequest {
    @NotNull
    private long oneToOneId;
    @NotNull
    private long authorId;
    @NotNull
    private long recipientId;
    private List<UserAnswerRequest> questions;
    private String message;
}
