package com.example.onetoone.core.user.results;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserPermissionResultModel {

    Long id;
    String code;
    String description;
}
