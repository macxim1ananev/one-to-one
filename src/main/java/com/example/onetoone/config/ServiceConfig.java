package com.example.onetoone.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ServiceConfig {

    private static final String LOCALE_ERRORS_PATH = "classpath:/onetoone";
    private static final String LOCALE_LANGUAGE_PATH = "classpath:/errors";
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(LOCALE_ERRORS_PATH, LOCALE_LANGUAGE_PATH);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
