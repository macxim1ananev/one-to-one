package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.feedback.results.UserTechnologyStatisticsResult;
import com.example.onetoone.presentation.view.UserTechnologyStatisticsView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = UsersTechnologyStatisticsViewMapper.class)
public interface UsersTechnologyStatisticsViewMapper {
    UserTechnologyStatisticsView toView(UserTechnologyStatisticsResult result);
}
