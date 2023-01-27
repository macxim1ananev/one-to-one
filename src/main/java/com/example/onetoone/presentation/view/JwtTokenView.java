package com.example.onetoone.presentation.view;

import lombok.Value;

@Value
public class JwtTokenView {
    String jwtToken;
    String refreshToken;
}
