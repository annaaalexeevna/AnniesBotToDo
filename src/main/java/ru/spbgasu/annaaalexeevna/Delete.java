package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;
import java.util.Map;

public class Delete extends BotCommand {

    private final SendMessage sendMessage;
    Map<User, ClassOfArrayLists> usersToDoList;

    public Delete(String commandIdentifier, String description, Map<User, ClassOfArrayLists> usersToDoList) {
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
            case "task":
                classOfArrayLists.deleteTask(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]),
                        Integer.parseInt(arguments[4]));
                sendMessage.setText("Вы успешно удалили задание!");
                ToDoBot.trySendMessage(absSender, user, sendMessage);
                break;
            case "list":
                classOfArrayLists.deleteList(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]));
                sendMessage.setText("Вы успешно удалили список и задания, которые содержались в нем!");
                ToDoBot.trySendMessage(absSender, user, sendMessage);
                break;
            case "group":
                classOfArrayLists.deleteGroup(Integer.parseInt(arguments[2]));
                sendMessage.setText("Вы успешно удалили группу и все списки и задания, которые содержались в ней!");
                ToDoBot.trySendMessage(absSender, user, sendMessage);
                break;
        }
    }
}
