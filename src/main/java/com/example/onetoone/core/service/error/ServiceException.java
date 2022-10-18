package com.example.onetoone.core.service.error;

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
        this.errorCode = exception.getCode();
    }

    @Getter
    public static enum Exception {
        USER_NOT_FOUND(1, "User not found"),
        ONE_TO_ONE_NOT_FOUND(2, "One to pne not found"),
        ONE_TO_ONE_NOT_VALID(3, "One to one not valid, you can't create one to one with yourself"),
        FEEDBACK_NOT_VALID(4, "User can't write feedback for yourself"),
        FEEDBACK_NOT_CREATE(5, "Feedback not created" ),
        ONE_TO_ONE_CLOSE_IMPOSSIBLE(6, "Only one to one author can close one to one");


        Exception(int code, String message) {
            this.code = code;
            this.message = message;
        }

        private final int code;
        private final String message;
    }

    public static Optional<Exception> fromString(Integer code){
        return Stream.of(ServiceException.Exception.values()).filter(c -> Objects.equals(code, c.name())).findFirst();
    }

    public static Optional<Exception> fromCode(Integer code) {
        return Stream.of(Exception.values()).filter(c -> c.code==(code)).findFirst();
    }
}
