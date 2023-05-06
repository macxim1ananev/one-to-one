package com.example.onetoone.core.user.entities;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Permissions {
    @FieldNameConstants.Include CREATE_ONE_TO_ONE,
    @FieldNameConstants.Include GET_ONE_TO_ONE,
    @FieldNameConstants.Include UPDATE_ONE_TO_ONE,
    @FieldNameConstants.Include ACCEPT_ONE_TO_ONE,
    @FieldNameConstants.Include CLOSE_ONE_TO_ONE,
    @FieldNameConstants.Include DELETE_ONE_TO_ONE,
    @FieldNameConstants.Include GET_ALL_ONE_TO_ONE,
    @FieldNameConstants.Include GET_ALL_USER_ONE_TO_ONE,
    @FieldNameConstants.Include CREATE_FEEDBACK,
    @FieldNameConstants.Include GET_FEEDBACK_BY_ONE_TO_ONE_AND_RECIPIENT_ID,
    @FieldNameConstants.Include GET_ALL_USER_STATISTICS,
    @FieldNameConstants.Include GET_USER_STATISTICS,
    @FieldNameConstants.Include GET_FULL_USER_STATISTICS,
    @FieldNameConstants.Include GET_USER_TECHNOLOGY_STATISTICS,
    @FieldNameConstants.Include GET_ALL_USER_TECHNOLOGY_STATISTICS,
    @FieldNameConstants.Include ADD_LIST_QUESTIONS,
    @FieldNameConstants.Include GET_QUESTION,
    @FieldNameConstants.Include UPDATE_QUESTION,
    @FieldNameConstants.Include GET_ALL_QUESTIONS,
    @FieldNameConstants.Include ADD_TECHNOLOGY,
    @FieldNameConstants.Include GET_TECHNOLOGY,
    @FieldNameConstants.Include GET_ALL_TECHNOLOGY,
    @FieldNameConstants.Include GET_USER,
    @FieldNameConstants.Include GET_ALL_USER_PERMISSION,
    @FieldNameConstants.Include GET_USER_PERMISSION,
    @FieldNameConstants.Include GET_ALL_USER_ROLE,
    @FieldNameConstants.Include GET_USER_ROLE
}
