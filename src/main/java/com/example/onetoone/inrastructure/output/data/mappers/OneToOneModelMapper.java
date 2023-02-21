package com.example.onetoone.inrastructure.output.data.mappers;

import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.inrastructure.output.data.models.OneToOneModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = OneToOneStatus.class
)
public interface OneToOneModelMapper {
    @Mapping(target = "id", source = "id", nullValuePropertyMappingStrategy =NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "levelId", source = "level.id")
    OneToOneModel toModel(OneToOne entity);

    @Mapping(target = "status", expression = "java(OneToOneStatus.fromId(model.getStatusId()).orElse(null))")
    @Mapping(target = "level", expression = "java(OneToOneLevel.fromId(model.getLevelId()).orElse(null))")
    OneToOne toEntity(OneToOneModel model);
}
