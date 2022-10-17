package com.example.onetoone.core.service.common;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GetListInteractor {

    protected ListFilter getListFilter(GetFilteredAndSortedListCommand command, Class<?> entityType) {
        return getListFilter(command, entityType, Stream::empty);
    }

    protected ListFilter getListFilter(GetFilteredAndSortedListCommand command, Class<?> entityType, Supplier<Stream<SearchCriteria>> additionalCriteria) {
        return ListFilter.builder()
                .page(command.getPage())
                .size(command.getSize())
                .direction(ListFilter.getDirection(command.getSort()))
                .sortBy(ListFilter.getSortBy(command.getSort()))
                .criteria(getCriteria(command, additionalCriteria))
                .type(entityType)
                .build();
    }

    private List<SearchCriteria> getCriteria(GetFilteredAndSortedListCommand command, Supplier<Stream<SearchCriteria>> additionalCriteria) {
        return Stream.concat(command.getCriteria().stream(),
                additionalCriteria.get()).collect(Collectors.toList());
    }
}
