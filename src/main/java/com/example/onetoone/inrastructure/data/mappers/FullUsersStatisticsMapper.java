package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.feedback.entities.statistics.UserTechnologyStatistics;
import com.example.onetoone.core.feedback.results.statistics.FullUserStatisticsResult;
import com.example.onetoone.core.feedback.results.statistics.UserTechnologyStatisticsResult;
import com.example.onetoone.inrastructure.data.models.UserTechnologyStatisticsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UsersStatisticsMapper.class}
)
public interface FullUsersStatisticsMapper {
    UserTechnologyStatisticsModel toModel(UserTechnologyStatistics statistics);

    UserTechnologyStatistics toEntity(UserTechnologyStatisticsModel model);

    FullUserStatisticsResult toFullUserStatisticsResult(UserTechnologyStatistics statistics);

    @Mapping(target = "user", source = "userStatistics.user")
    UserTechnologyStatisticsResult toTechnologyStatisticsResult(UserTechnologyStatistics userTechnologyStatistics);
}
