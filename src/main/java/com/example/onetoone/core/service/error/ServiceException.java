package com.example.onetoone.core.service.error;

import com.example.onetoone.core.service.common.StaticMessageSource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Value
public class ServiceException extends RuntimeException {

    int errorCode;

    public ServiceException(Exception exception, Object... args) {
        super(StaticMessageSource.getMessage(exception.getLabel(), args));
        this.errorCode = exception.getCode();
    }

    @Getter
    public static enum Exception {
        USER_NOT_FOUND(1, "onetoone.exception.user.not.found"),
        ONE_TO_ONE_NOT_FOUND(2, "onetoone.exception.onetoone.not.found"),
        ONE_TO_ONE_NOT_VALID(3, "onetoone.exception.onetoone.not.valid"),
        FEEDBACK_NOT_VALID(4, "onetoone.exception.feedback.not.valid"),
        FEEDBACK_NOT_CREATE(5, "onetoone.exception.feedback.not.create" ),
        ONE_TO_ONE_CLOSE_IMPOSSIBLE(6, "onetoone.exception.onetoone.close.impossible"),
        QUESTION_NOT_FOUND(7, "onetoone.exception.question.not.found"),
        TECHNOLOGY_NOT_FOUND(8, "onetoone.exception.technology.not.found"),
        USER_STATISTICS_NOT_FOUND(9, "onetoone.exception.user-statistics.not.found" ),
        VERIFICATION_TOKEN_HAS_EXPIRED(10, "onetoone.exception.user.verification.token.expired" ),
        INVALID_TOKEN_FOR_CONFIRM_REGISTRATION_USER(11, "onetoone.exception.user.verification.token.invalid" ),
        USER_BY_EMAIL_NOT_FOUND(12, "onetoone.exception.user.by.email.not.found" ),
        TOKEN_INVALID_MESS(13, "onetoone.exception.jwt.token.invalid"),
        USER_BY_EMAIL_ALREADY_REGISTERED(14,"onetoone.exception.user.by.email.already.registered" ),
        FEEDBACK_ALREADY_HAS_BEEN_WRITTEN(15, "onetoone.exception.feedback.has.been.written");


        Exception(int code, String label) {
            this.code = code;
            this.label = label;
        }

        private final int code;
        private final String label;
    }

    public static Optional<Exception> fromString(Integer code){
        return Stream.of(ServiceException.Exception.values()).filter(c -> Objects.equals(code, c.name())).findFirst();
    }

    public static Optional<Exception> fromCode(Integer code) {
        return Stream.of(Exception.values()).filter(c -> c.code==(code)).findFirst();
    }
}
