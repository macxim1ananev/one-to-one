package com.example.onetoone.core.statistics;

import com.example.onetoone.core.statistics.entity.UserTechnologyStatistics;
import com.example.onetoone.core.statistics.result.FullUserStatisticsResult;
import com.example.onetoone.core.statistics.result.UserTechnologyStatisticsResult;
import com.example.onetoone.core.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UserMapper.class})
public interface UserTechnologyStatisticMapper {
    @Mapping(target = "user", source = "userStatistics.user")
    UserTechnologyStatisticsResult toResult(UserTechnologyStatistics userTechnologyStatistics);

    @Mapping(target = "user", source = "userStatistics.user")
    UserTechnologyStatisticsResult toTechnologyStatisticsResult(UserTechnologyStatistics userTechnologyStatistics);
    FullUserStatisticsResult toFullUserStatisticsResult(UserTechnologyStatistics statistics);
}
