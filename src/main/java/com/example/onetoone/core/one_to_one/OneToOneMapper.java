package com.example.onetoone.core.one_to_one;

import com.example.onetoone.core.one_to_one.commands.CreateOneToOneCommand;
import com.example.onetoone.core.one_to_one.commands.CreateOneToOneTelegramCommand;
import com.example.onetoone.core.one_to_one.commands.UpdateOneToOneCommand;
import com.example.onetoone.core.one_to_one.entities.FeedbackStatus;
import com.example.onetoone.core.one_to_one.entities.OneToOne;
import com.example.onetoone.core.one_to_one.entities.OneToOneLevel;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.technology.entities.Technology;
import com.example.onetoone.core.user.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {OneToOneStatus.class, OneToOneLevel.class, FeedbackStatus.class}
)
public interface OneToOneMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "initiator", source = "initiator")
    @Mapping(target = "technology", source = "technology")
    @Mapping(target = "dateTime", source = "command.dateTime")
    @Mapping(target = "status", expression = "java(OneToOneStatus.OPEN)")
    @Mapping(target = "comment", source = "command.comment")
    @Mapping(target = "level", expression = "java(OneToOneLevel.fromId(command.getLevelId()).orElse(null))")
    @Mapping(target = "initiatorFeedback", expression = "java(FeedbackStatus.NO_WRITE)")
    @Mapping(target = "opponentFeedback", expression = "java(FeedbackStatus.NO_WRITE)")
    OneToOne toEntity(CreateOneToOneCommand command, User initiator, Technology technology);

    @Mapping(target = "initiatorId", source = "initiator.id")
    @Mapping(target = "opponentId", source = "opponent.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "level", source = "level")
    OneToOneResult toResult(OneToOne entity);

    @Mapping(target = "technology", source = "technology")
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget OneToOne entity, UpdateOneToOneCommand command, Technology technology);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "initiator", source = "initiator")
    @Mapping(target = "technology", source = "technology")
    @Mapping(target = "dateTime", source = "command.dateTime")
    @Mapping(target = "status", expression = "java(OneToOneStatus.OPEN)")
    @Mapping(target = "comment", source = "command.comment")
    @Mapping(target = "level", expression = "java(OneToOneLevel.fromId(command.getLevelId()).orElse(null))")
    @Mapping(target = "initiatorFeedback", expression = "java(FeedbackStatus.NO_WRITE)")
    @Mapping(target = "opponentFeedback", expression = "java(FeedbackStatus.NO_WRITE)")
    OneToOne toEntity(CreateOneToOneTelegramCommand command, User initiator, Technology technology);
}