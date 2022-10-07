package com.example.onetoone.presentation.mapper;

import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.presentation.view.OneToOneView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OneToOneViewMapper {
    OneToOneView toView(OneToOneResult result);
}
