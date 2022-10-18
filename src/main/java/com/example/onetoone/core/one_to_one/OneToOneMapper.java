package com.example.onetoone.core.one_to_one;

import com.example.onetoone.core.one_to_one.commands.CreateOneToOneCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OneToOneMapper {
    OneToOne toEntity(CreateOneToOneCommand command);

    @Mapping(target = "initiatorId", source = "initiator.id")
    @Mapping(target = "opponentId", source = "opponent.id")
    @Mapping(target = "status", source = "status")
    OneToOneResult toResult(OneToOne entity);
}
