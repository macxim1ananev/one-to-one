package com.example.onetoone.inrastructure.data.criteria;

import com.example.onetoone.core.service.common.ListFilter;
import com.example.onetoone.core.service.common.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class GenericSpecificationsBuilder<T> {

    private final ListFilter listFilter;

    public Specification<T> build() {
        Specification<T> result = null;
        if (!listFilter.getCriteria().isEmpty()) {
            result = new GenericSpecification<>(listFilter.getCriteria().get(0), listFilter.getType());
            for (int index = 1; index < listFilter.getCriteria().size(); ++index) {
                SearchCriteria searchCriteria = listFilter.getCriteria().get(index);
                result = searchCriteria.isOrOperation() ?
                        Specification.where(result).or(new GenericSpecification<>(searchCriteria, listFilter.getType())) :
                        Specification.where(result).and(new GenericSpecification<>(searchCriteria, listFilter.getType()));
            }
        }
        return result;
    }
}