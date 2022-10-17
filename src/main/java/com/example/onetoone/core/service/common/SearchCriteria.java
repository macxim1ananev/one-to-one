package com.example.onetoone.core.service.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchCriteria {
    private final String key;
    private final String operation;
    private final Object value;
    private final boolean isOrOperation;

    public static class SearchCriteriaBuilder {

        public SearchCriteriaBuilder notEqualOperation() {
            this.operation = "!:";
            return this;
        }
    }
}