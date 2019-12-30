package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Map;

public class MarkReady extends BotCommand {

    private final SendMessage sendMessage;
    Map<User, ClassOfArrayLists> usersToDoList;

    public MarkReady(String commandIdentifier, String description, Map<User, ClassOfArrayLists> usersToDoList) {
        super(commandIdentifier, description);
        this.usersToDoList = usersToDoList;
        sendMessage = new SendMessage();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        if (!usersToDoList.containsKey(user)) {
            usersToDoList.put(user, new ClassOfArrayLists());
        }
        ClassOfArrayLists classOfArrayLists = usersToDoList.get(user);
        classOfArrayLists.markReady(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]));
        sendMessage.setText("Вы выполнили задание! Поздравляю!");
        ToDoBot.trySendMessage(absSender, user, sendMessage);
    }
}
