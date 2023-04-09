package com.example.onetoone.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
public class UpdateOneToOneRequest {
    @NotNull
    private Long id;
    @NotNull
    private Long technologyId;
    @NotNull
    private Integer levelId;
    @NotNull
    private LocalDateTime dateTime;
    private String comment;
}