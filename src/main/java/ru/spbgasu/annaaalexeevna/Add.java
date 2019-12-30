package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add extends BotCommand {

    private final SendMessage sendMessage;
    Map<User, ClassOfArrayLists> usersToDoList;

    public Add(String commandIdentifier, String description, Map<User, ClassOfArrayLists> usersToDoList) {
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
        for (int i = 0; i <= arguments.length; i++) {
            switch (arguments[i]) {
                case "task":
                    try {
                        Task newTask = new Task(Integer.parseInt(arguments[i + 1]), Integer.parseInt(arguments[i + 2]),
                                Integer.parseInt(arguments[i + 3]), arguments[i + 4], arguments[i + 5],
                                new SimpleDateFormat("dd/MM/yyyy").parse(arguments[i + 3]), false);
                        classOfArrayLists.addTask(newTask);
                        sendMessage.setText("Новое задание успешно создано!");
                        ToDoBot.trySendMessage(absSender, user, sendMessage);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "list":
                    TaskList newTaskList = new TaskList(Integer.parseInt(arguments[i + 1]), Integer.parseInt(arguments[i + 2]),
                            arguments[i + 3]);
                    classOfArrayLists.addList(newTaskList);
                    sendMessage.setText("Новый лист успешно создан!");
                    ToDoBot.trySendMessage(absSender, user, sendMessage);
                    break;
                case "group":
                    GroupOfTask newGroupOfTask = new GroupOfTask(Integer.parseInt(arguments[i + 1]), arguments[i + 2]);
                    classOfArrayLists.addGroup(newGroupOfTask);
                    sendMessage.setText("Новая группа успешно создана!");
                    ToDoBot.trySendMessage(absSender, user, sendMessage);
                    break;
            }
        }
    }
}
