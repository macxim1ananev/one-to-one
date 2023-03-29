package com.example.onetoone.core.technology;

import com.example.onetoone.core.technology.commands.AddTechnologyCommand;
import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.core.technology.results.TechnologyResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TechnologyMapper {
    Technology toEntity(AddTechnologyCommand command);
    TechnologyResult toResult(Technology technology);
}
