package com.example.onetoone.presentation.request;

import lombok.Data;

@Data
public class RefreshJwtTokenRequest {
    private String refreshToken;
}
