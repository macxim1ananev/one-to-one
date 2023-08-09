package com.example.onetoone.core.statistics;

import com.example.onetoone.core.statistics.entity.UserStatistics;
import com.example.onetoone.core.statistics.result.UserStatisticsResult;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.inrastructure.output.data.models.UsersStatisticsModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UserMapper.class})
public interface UsersStatisticsMapper {
    UserStatistics toEntity(UsersStatisticsModel model);

    UsersStatisticsModel toModel(UserStatistics statistics);

    UserStatisticsResult toResult(UserStatistics entity);
}
