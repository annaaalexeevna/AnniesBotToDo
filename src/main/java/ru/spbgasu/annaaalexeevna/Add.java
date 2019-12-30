package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Add extends BotCommand {

    private final SendMessage sendMessage;
    ClassOfArrayLists classOfArrayLists;

    public Add(String commandIdentifier, String description, ClassOfArrayLists classOfArrayLists) {
        super(commandIdentifier, description);
        this.classOfArrayLists = classOfArrayLists;
        sendMessage = new SendMessage();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        for (int i = 0; i <= arguments.length; i++) {
            switch (arguments[i]) {
                case "task":
                    try {
                        Task newTask = new Task(Integer.parseInt(arguments[i + 1]), Integer.parseInt(arguments[i + 2]),
                                Integer.parseInt(arguments[i + 3]), arguments[i + 4], arguments[i + 5], user,
                                new SimpleDateFormat("dd/MM/yyyy").parse(arguments[i + 3]), false);
                        classOfArrayLists.addTask(newTask);
                        sendMessage.setText("Новое задание успешно создано!");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "list":
                    TaskList newTaskList = new TaskList(Integer.parseInt(arguments[i + 1]), Integer.parseInt(arguments[i + 2]), arguments[i + 3] );
                    classOfArrayLists.addList(newTaskList);
                    sendMessage.setText("Новый лист успешно создан!");
                    break;
                case "group":
                    GroupOfTask newGroupOfTask = new GroupOfTask(Integer.parseInt(arguments[i + 1]), arguments[i + 2]);
                    classOfArrayLists.addGroup(newGroupOfTask);
                    sendMessage.setText("Новая группа успешно создана!");
                    break;
            }
        }
    }
}
