package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class Edit extends BotCommand {
    private final SendMessage sendMessage;
    Map<User, ClassOfArrayLists> usersToDoList;

    public Edit(String commandIdentifier, String description, Map<User, ClassOfArrayLists> usersToDoList) {
        super(commandIdentifier, description);
        sendMessage = new SendMessage();
        this.usersToDoList = usersToDoList;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        if (!usersToDoList.containsKey(user)) {
            usersToDoList.put(user, new ClassOfArrayLists());
        }
        ClassOfArrayLists classOfArrayLists = usersToDoList.get(user);
        switch (arguments[1]) {
            case ("task"):
                classOfArrayLists.editTask(arguments[2], Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]),
                        Integer.parseInt(arguments[5]), arguments[6]);
                sendMessage.setText("Вы успешно отредактировали задание!");
                ToDoBot.trySendMessage(absSender, user, sendMessage);
                break;
            case ("list"):
                classOfArrayLists.editList(arguments[2], Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]),
                        arguments[5]);
                sendMessage.setText("Вы успешно отредактировали список!");
                ToDoBot.trySendMessage(absSender, user, sendMessage);
                break;
            case ("group"):
                classOfArrayLists.editGroup(arguments[2], Integer.parseInt(arguments[3]), arguments[4]);
                sendMessage.setText("Вы успешно отредактировали группу!");
                ToDoBot.trySendMessage(absSender, user, sendMessage);
                break;
        }
    }
}
