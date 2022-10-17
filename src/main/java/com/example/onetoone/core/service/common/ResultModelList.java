package com.example.onetoone.core.service.common;

import lombok.Value;

import java.util.List;

@Value
public class ResultModelList<T> {

    long totalItems;
    List<T> items;
}
