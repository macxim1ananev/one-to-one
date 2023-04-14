package com.example.onetoone.core.user.entities;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Permissions {
    @FieldNameConstants.Include CREATE_ONE_TO_ONE,
    @FieldNameConstants.Include CREATE_QUESTIONS,
    @FieldNameConstants.Include CREATE_FEEDBACK,
    @FieldNameConstants.Include FEEDBACK_VIEW,
    @FieldNameConstants.Include ONE_TO_ONE_VIEW,
    @FieldNameConstants.Include QUESTIONS_VIEW;
}
