package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateOneToOneRequest {
    @NotNull
    private long initiatorId;
    @NotNull
    private Long technologyId;
    @NotNull
    private LocalDateTime dateTime;
    private String comment;
}
