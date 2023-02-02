package com.example.onetoone.presentation.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class AcceptOneToOneRequest {
    @NonNull
    private Long opponentId;
    @NonNull
    private Long oneToOneId;
}
