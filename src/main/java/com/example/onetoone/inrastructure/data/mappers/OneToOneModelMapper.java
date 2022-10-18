package com.example.onetoone.inrastructure.data.mappers;

import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.inrastructure.data.models.OneToOneModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OneToOneModelMapper {
    @Mapping(target = "id", source = "id", nullValuePropertyMappingStrategy =NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "programmingLanguageId", source = "programmingLanguage.id")
    @Mapping(target = "statusId", source = "status.id")
    OneToOneModel toModel(OneToOne entity);

    @Mapping(target = "programmingLanguage", expression = "java(ProgrammingLanguage.fromId(model.getProgrammingLanguageId()).orElse(null))")
    @Mapping(target = "status", expression = "java(OneToOneStatus.fromId(model.getStatusId()).orElse(null))")
    OneToOne toEntity(OneToOneModel model);
}
