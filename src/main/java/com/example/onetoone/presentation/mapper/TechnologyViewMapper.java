package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.technology.results.TechnologyResult;
import com.example.onetoone.presentation.view.TechnologyView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TechnologyViewMapper {
    TechnologyView toView(TechnologyResult resultModel);
}
