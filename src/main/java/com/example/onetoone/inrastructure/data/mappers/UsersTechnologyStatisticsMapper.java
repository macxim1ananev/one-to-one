package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.feedback.rating.entity.UserTechnologyStatistics;
import com.example.onetoone.core.feedback.results.UserTechnologyStatisticsResult;
import com.example.onetoone.inrastructure.data.models.UserTechnologyStatisticsModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UsersStatisticsMapper.class}
)
public interface UsersTechnologyStatisticsMapper {
    UserTechnologyStatisticsModel toModel(UserTechnologyStatistics statistics);

    UserTechnologyStatistics toEntity(UserTechnologyStatisticsModel model);

    UserTechnologyStatisticsResult toResult(UserTechnologyStatistics statistics);
}
