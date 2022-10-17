package com.example.onetoone.presentation.common;

import lombok.Value;

import java.util.List;
@Value
public class ListView <T>{
    long totalItems;
    List<T> items;
}
