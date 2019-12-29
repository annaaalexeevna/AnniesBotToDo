package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;

public class MarkReady extends BotCommand {

    ArrayList<Task> arrayOfTasks;
    ArrayList<TaskList> arrayOfTaskLists;
    ArrayList<GroupOfTask> arrayGroupOfTasks;
    private final Main main;
    private final SendMessage sendMessage;

    public MarkReady(String commandIdentifier, String description, ArrayList<Task> arrayOfTasks, ArrayList<TaskList> arrayOfTaskLists, ArrayList<GroupOfTask> arrayGroupOfTasks, Main main) {
        super(commandIdentifier, description);
        this.arrayOfTasks = arrayOfTasks;
        this.arrayOfTaskLists = arrayOfTaskLists;
        this.arrayGroupOfTasks = arrayGroupOfTasks;
        this.main = main;
        sendMessage = new SendMessage();

    }
    //markready group list task
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        for (Task currentTask : arrayOfTasks) {
            if (Integer.parseInt(arguments[1]) == currentTask.getGroupNumber() &&
                    Integer.parseInt(arguments[2]) == currentTask.getListNumber() &&
                    Integer.parseInt(arguments[3]) == currentTask.getTaskNumber()) {
                currentTask.setIsReady(true);
                sendMessage.setText("Вы выполнили задание! Поздравляю!");
            }
        }
    }
}
