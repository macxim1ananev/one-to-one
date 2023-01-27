package com.example.onetoone.presentation.request;

import lombok.Value;

@Value
public class RefreshJwtTokenRequest {
    String refreshToken;
}
