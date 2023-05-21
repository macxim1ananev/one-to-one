package com.example.onetoone.inrastructure.output.data.mappers;

import com.example.onetoone.core.telegramchat.entity.TelegramChat;
import com.example.onetoone.inrastructure.output.data.models.TelegramChatModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TelegramChatModelMapper {
    TelegramChatModel toModel(TelegramChat telegramChat);

    TelegramChat toEntity(TelegramChatModel save);
}
