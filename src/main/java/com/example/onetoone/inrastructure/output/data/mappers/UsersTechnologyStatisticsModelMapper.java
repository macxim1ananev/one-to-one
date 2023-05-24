package com.example.onetoone.inrastructure.output.data.mappers;

import com.example.onetoone.core.statistics.UsersStatisticsMapper;
import com.example.onetoone.core.statistics.entities.UserTechnologyStatistics;
import com.example.onetoone.inrastructure.output.data.models.UserTechnologyStatisticsModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {UsersStatisticsMapper.class}
)
public interface UsersTechnologyStatisticsModelMapper {
    UserTechnologyStatisticsModel toModel(UserTechnologyStatistics statistics);

    UserTechnologyStatistics toEntity(UserTechnologyStatisticsModel model);
}
