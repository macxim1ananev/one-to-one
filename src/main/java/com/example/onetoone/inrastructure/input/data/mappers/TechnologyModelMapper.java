package com.example.onetoone.inrastructure.input.data.mappers;

import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.inrastructure.input.data.models.TechnologyModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TechnologyModelMapper {
    TechnologyModel toModel(Technology entity);

    Technology toEntity(TechnologyModel put);
}
