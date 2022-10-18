package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CloseOneToOneRequest {
    @NotNull
    private long authorId;
    @NotNull
    private long opponentId;
}
