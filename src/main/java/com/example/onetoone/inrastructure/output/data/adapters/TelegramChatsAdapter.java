package com.example.onetoone.inrastructure.output.data.adapters;

import com.example.onetoone.core.service.interfaces.TelegramChats;
import com.example.onetoone.core.telegramchat.entity.TelegramChat;
import com.example.onetoone.inrastructure.output.data.mappers.TelegramChatModelMapper;
import com.example.onetoone.inrastructure.output.data.repositories.TelegramChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramChatsAdapter implements TelegramChats {
    private final TelegramChatRepository repository;
    private final TelegramChatModelMapper mapper;
    @Override
    public TelegramChat put(TelegramChat telegramChat) {
        var model = mapper.toModel(telegramChat);
        return mapper.toEntity(repository.save(model));
    }
}
