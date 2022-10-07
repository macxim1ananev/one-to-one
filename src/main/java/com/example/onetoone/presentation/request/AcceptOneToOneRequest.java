package com.example.onetoone.presentation.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class AcceptOneToOneRequest {
    @NonNull
    private long opponentId;
    @NonNull
    private long oneToOneId;
}
