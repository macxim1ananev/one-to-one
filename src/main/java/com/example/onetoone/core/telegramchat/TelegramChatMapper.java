package com.example.onetoone.core.telegramchat;

import com.example.onetoone.core.telegramchat.entity.TelegramChat;
import com.example.onetoone.core.user.commands.UserRegistrationCommand;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TelegramChatMapper {
    TelegramChat toEntity(UserRegistrationCommand command);
}
