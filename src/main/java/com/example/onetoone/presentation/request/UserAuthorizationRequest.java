package com.example.onetoone.presentation.request;

import lombok.Value;

@Value
public class UserAuthorizationRequest {
    String email;
    String password;
}
