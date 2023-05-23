package com.example.onetoone.inrastructure.output.telegram.bot;

import com.example.onetoone.core.one_to_one.commands.CreateOneToOneTelegramCommand;
import com.example.onetoone.core.one_to_one.entities.OneToOneStatus;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.one_to_one.results.OneToOneTelegramResult;
import com.example.onetoone.core.user.commands.UserRegistrationCommand;
import com.example.onetoone.core.service.command_bus.CommandBus;
import com.example.onetoone.core.user.results.UserRegistrationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class TelegramGateWay extends TelegramLongPollingBot implements LongPollingBot {
    private static final String WELCOME_REGISTER_MESSAGE = "Здравствуйте, %s! Вы успешно зарегистрировались, теперь вы можете найти пару для тестового собеседования";
    public static final String ONE_TO_ONE_CREATE_MESSAGE = "Здравствуйте, %s! Вы успешно создали заявку на тестовое интервью, когда найдется пара, мы отправим вам уведомление в этом чате.";
    public static final String ONE_TO_ONE_CLOSED_MESSAGE = "Здравствуйте, %s! Вы успешно создали заявку на тестовое интервью, вот ваш оппонент по собеседованию %s";
    private final CommandBus commandBus;
    @Value("${telegram.bot.username}")
    private String userName;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        String receivedMessage;

        //если получено сообщение текстом
        if(update.hasMessage()) {
            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, update.getMessage().getFrom(), update.getMessage().getChat());
            }
            //если нажата одна из кнопок бота
        } else if (update.hasCallbackQuery()) {
            receivedMessage = update.getCallbackQuery().getData();
            botAnswerUtils(receivedMessage, update.getChatMember().getFrom(), update.getChatMember().getChat());
        }
    }

    private void botAnswerUtils(String receivedMessage, User user, Chat chat) {
        switch (receivedMessage){
            case "/register":
                registerCommand(user, chat);
                break;
            case "/create":
                create(user, chat);
                break;
            case "/getall":
                //getOneToOneList();
                break;
            case "accept":
                //acceptOneToone
                break;
            default: break;
        }
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private void registerCommand(User user, Chat chat){
        var res = commandBus.execute(UserRegistrationCommand.builder()
                .telegramUserName(user.getUserName())
                .telegramUserId(user.getId())
                .telegramChatId(chat.getId())
                .name(user.getFirstName())
                .password("password")
                .surName(user.getLastName())
                .build());

        sendAnswer(String.format(WELCOME_REGISTER_MESSAGE,user.getUserName()), chat.getId());
    }

    private void create(User user, Chat chat){
        OneToOneTelegramResult res = commandBus.execute(CreateOneToOneTelegramCommand
                .builder()
                        .dateTime(LocalDateTime.now())
                        .levelId(1)
                        .telegramUserId(user.getId())
                        .technologyId(7L)

                .build());

        if (res.getStatus().equals(OneToOneStatus.CLOSED.name())){
            sendAnswer(String.format(ONE_TO_ONE_CLOSED_MESSAGE, user.getUserName(), res.getUserName()), chat.getId());
        } else {
            sendAnswer(String.format(ONE_TO_ONE_CREATE_MESSAGE,user.getUserName()), chat.getId());
        }
    }

    private void sendAnswer(String message, Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
