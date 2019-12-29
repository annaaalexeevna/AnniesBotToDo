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

    ArrayList<Task> arrayOfTasks;
    ArrayList<TaskList> arrayOfTaskLists;
    ArrayList<GroupOfTask> arrayGroupOfTasks;

    public Show(String commandIdentifier, String description, ArrayList<Task> arrayOfTasks, ArrayList<TaskList> arrayOfTaskLists, ArrayList<GroupOfTask> arrayGroupOfTasks) {
        super(commandIdentifier, description);
        this.arrayOfTasks = arrayOfTasks;
        this.arrayOfTaskLists = arrayOfTaskLists;
        this.arrayGroupOfTasks = arrayGroupOfTasks;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String finalString = "";
        for (GroupOfTask currentGroup : arrayGroupOfTasks) {
            finalString += currentGroup.getGroupNumber() + ". " + currentGroup.getNameGroup() + '\n';
            for (TaskList currentTaskList : arrayOfTaskLists) {
                if (currentGroup.getGroupNumber() == currentTaskList.getGroupNumber()) {
                    finalString += '\t' + currentTaskList.getTaskListNumber() + ". " + currentTaskList.getNameTaskList() + '\n';
                    for (Task currentTask : arrayOfTasks) {
                        if (currentTaskList.getTaskListNumber() == currentTask.getListNumber()) {
                            finalString += '\t' + '\t' + currentTask.getTaskNumber() + ". " + currentTask.getNameTask() + (currentTask.getIsReady() ? " - выполнено" : " - не выполнено")  + '\n';
                        }
                    }
                }
            }
        }
        SendMessage sendMessage = new SendMessage(chat.getId(), finalString);
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
