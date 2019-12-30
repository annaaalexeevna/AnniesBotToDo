package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;


public class Show extends BotCommand {

    Map<User, ClassOfArrayLists> usersToDoList;

    public Show(String commandIdentifier, String description, Map<User, ClassOfArrayLists> usersToDoList) {
        super(commandIdentifier, description);
        this.usersToDoList = usersToDoList;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        if (!usersToDoList.containsKey(user)) {
            usersToDoList.put(user, new ClassOfArrayLists());
        }
        ClassOfArrayLists classOfArrayLists = usersToDoList.get(user);
        SendMessage sendMessage = new SendMessage(chat.getId(), classOfArrayLists.show());
        ToDoBot.trySendMessage(absSender, user, sendMessage);
    }
}
