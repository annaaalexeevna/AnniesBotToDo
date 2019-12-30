package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Show extends BotCommand {

    ClassOfArrayLists classOfArrayLists;

    public Show(String commandIdentifier, String description, ClassOfArrayLists classOfArrayLists) {
        super(commandIdentifier, description);
        this.classOfArrayLists = classOfArrayLists;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        SendMessage sendMessage = new SendMessage(chat.getId(), classOfArrayLists.show());
        trySendMessage(absSender, sendMessage);
    }

    private void trySendMessage(AbsSender absSender, SendMessage sendMessage) {
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
