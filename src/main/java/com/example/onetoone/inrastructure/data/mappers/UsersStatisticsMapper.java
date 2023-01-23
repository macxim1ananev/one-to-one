package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.feedback.rating.entity.UserStatistics;
import com.example.onetoone.core.feedback.results.UserStatisticsResult;
import com.example.onetoone.core.user.UserMapper;
import com.example.onetoone.inrastructure.data.models.UsersStatisticsModel;
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
