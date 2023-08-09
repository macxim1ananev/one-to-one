package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.statistics.result.FullUserStatisticsResult;
import com.example.onetoone.core.statistics.result.UserTechnologyStatisticsResult;
import com.example.onetoone.presentation.view.FullUserStatisticsView;
import com.example.onetoone.presentation.view.UserTechnologyStatisticsView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = FullUsersStatisticsViewMapper.class)
public interface FullUsersStatisticsViewMapper {
    FullUserStatisticsView toView(FullUserStatisticsResult result);

    UserTechnologyStatisticsView toTechnologyStatisticsView(UserTechnologyStatisticsResult result);
}
