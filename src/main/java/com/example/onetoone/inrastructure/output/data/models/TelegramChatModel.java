package com.example.onetoone.inrastructure.output.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "telegram_chat", schema = "public")
public class TelegramChatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long telegramChatId;
    private Long telegramUserId;
}
