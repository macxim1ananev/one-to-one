package com.example.onetoone.inrastructure.output.data.criteria;

import com.example.onetoone.core.service.common.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class GenericSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;
    private Class<?> type;

    public GenericSpecification(final SearchCriteria criteria, final Class<?> type) {
        super();
        this.criteria = criteria;
        this.type = type;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        var operation = criteria.getOperation();
        switch (operation) {
            case ">":
                return criteriaBuilder.greaterThan(getPath(root), getValue());
            case ">:":
                return criteriaBuilder.greaterThanOrEqualTo(getPath(root), getValue());
            case "<":
                return criteriaBuilder.lessThan(getPath(root), getValue());
            case "<:":
                return criteriaBuilder.lessThanOrEqualTo(getPath(root), getValue());
            case "!:":
                return criteriaBuilder.notEqual(getPath(root), getValue());
            case ":":
                if (getPath(root).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            getPath(root), "%" + getValue() + "%");
                } else {
                    return criteriaBuilder.equal(getPath(root), getValue());
                }
            default:
                return null;
        }
    }

    private Comparable getValue() {
        var value = (Comparable) criteria.getValue();
        var field = criteria.getKey();
        try {
            var fieldType = searchField(field).getType();
            if (fieldType.isEnum()) {
                var enumValue = Enum.valueOf((Class<Enum>) fieldType, String.valueOf(value));
                value = (Comparable) enumValue.getClass().getMethod("getId").invoke(enumValue);
            }
        } catch (Exception ignored) {
        }
        return value;
    }

    private Expression getPath(Root<T> root) {
        var field = criteria.getKey();
        try {
            if (searchField(field).getType().isEnum()) {
                field = field + "Id";
            }
        } catch (Exception ignored) {
        }
        if (field.contains(".")) {
            return root.join(field.split("\\.")[0]).get(field.split("\\.")[1]);
        }
        return root.get(field);
    }

    private Field searchField(String name) {
        var allFields = ArrayUtils.addAll(type.getDeclaredFields(), type.getSuperclass().getDeclaredFields());
        for (Field field : allFields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }
}