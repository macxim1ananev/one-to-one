package com.example.onetoone.core.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class StaticMessageSource {

    private static MessageSource messageSource;

    @Autowired
    public StaticMessageSource(MessageSource messageSource) {
        StaticMessageSource.messageSource = messageSource;
    }

    public static String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }
}
