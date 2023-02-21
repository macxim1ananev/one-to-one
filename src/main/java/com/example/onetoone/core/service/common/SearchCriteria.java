package com.example.onetoone.core.service.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchCriteria {
    private final String key;
    private final String operation;
    private final Object value;

    public static class SearchCriteriaBuilder {

        public SearchCriteriaBuilder notEqualOperation() {
            this.operation = "!:";
            return this;
        }
    }
}