package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.feedback.entities.Feedback;
import com.example.onetoone.inrastructure.data.models.FeedbackModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface FeedbackModelMapper {
    FeedbackModel toModel(Feedback feedback);

    Feedback toEntity(FeedbackModel model);
}
