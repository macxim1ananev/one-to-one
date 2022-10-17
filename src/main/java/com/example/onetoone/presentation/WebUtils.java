package com.example.onetoone.presentation;

import com.example.onetoone.core.service.common.SearchCriteria;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtils {

    private static final String LOCAL_DATE_TIME_PATTERN = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})";
    private static final List<String> OR_OPERATION_LIST = List.of(":", "!:");

    public static Set<SearchCriteria> getCriteria(String search) {
        var pattern = Pattern.compile("(\\w+?\\.?\\w+?)(:|>|>:|<|<:)((\\w+?(@(\\w+?\\.?)?)?\\w*)|" +
                LOCAL_DATE_TIME_PATTERN + "),");
        var matcher = pattern.matcher(search + ",");
        var criteria = new HashSet<SearchCriteria>();
        while (matcher.find()) {
            var operation = matcher.group(2);
            criteria.add(SearchCriteria.builder()
                    .key(matcher.group(1))
                    .operation(matcher.group(2))
                    .value(getValue(matcher.group(3)))
                    .isOrOperation(isOrOperation(operation) && countMatches(search, matcher.group(1)) > 1)
                    .build());
        }
        return criteria;
    }

    private static Object getValue(String group) {
        var localDateTimePattern = Pattern.compile(LOCAL_DATE_TIME_PATTERN);
        if (localDateTimePattern.matcher(group).matches())
            return LocalDateTime.parse(group);
        return group;
    }

    private static int countMatches(String search, String field) {
        Pattern pattern = Pattern.compile(field + "(:|>|>:|<|<:)");
        Matcher matcher = pattern.matcher(search);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private static boolean isOrOperation(String operation) {
        return OR_OPERATION_LIST.contains(operation);
    }
}
