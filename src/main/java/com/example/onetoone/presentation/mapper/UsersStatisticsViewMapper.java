package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.statistics.results.UserStatisticsResult;
import com.example.onetoone.presentation.view.UserStatisticsView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsersStatisticsViewMapper {
    UserStatisticsView toView(UserStatisticsResult userStatisticsResult);
}
