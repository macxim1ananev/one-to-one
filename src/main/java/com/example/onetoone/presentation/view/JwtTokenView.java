package com.example.onetoone.presentation.view;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JwtTokenView {
    String jwtToken;
    String email;
    Long id;
}
