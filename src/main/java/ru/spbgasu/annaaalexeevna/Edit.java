package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Edit extends BotCommand {
    private final Main main;
    private final SendMessage sendMessage;
    ArrayList<Task> arrayOfTasks;
    ArrayList<TaskList> arrayOfTaskLists;
    ArrayList<GroupOfTask> arrayGroupOfTasks;

    public Edit(String commandIdentifier, String description, ArrayList<Task> arrayOfTasks, ArrayList<TaskList> arrayOfTaskLists, ArrayList<GroupOfTask> arrayGroupOfTasks, Main main) {
        super(commandIdentifier, description);
        this.arrayOfTasks = arrayOfTasks;
        this.arrayOfTaskLists = arrayOfTaskLists;
        this.arrayGroupOfTasks = arrayGroupOfTasks;
        this.main = main;
        sendMessage = new SendMessage();
    }

    //edit task/list/group "what" "where - number group list task" "new value"
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        switch (arguments[1]) {
            case ("task"): {
                switch (arguments[2]) {
                    case ("name"):
                        for (Task currentTask : arrayOfTasks) {
                            if (Integer.parseInt(arguments[3]) == currentTask.getGroupNumber() &&
                                    Integer.parseInt(arguments[4]) == currentTask.getListNumber() &&
                                    Integer.parseInt(arguments[5]) == currentTask.getTaskNumber()) {
                                currentTask.setNameTask(arguments[6]);
                            }
                        }
                        break;
                    case ("description"):
                        for (Task currentTask : arrayOfTasks) {
                            if (Integer.parseInt(arguments[3]) == currentTask.getGroupNumber() &&
                                    Integer.parseInt(arguments[4]) == currentTask.getListNumber() &&
                                    Integer.parseInt(arguments[5]) == currentTask.getTaskNumber()) {
                                currentTask.setDescriptionTask(arguments[6]);
                            }
                        }
                        break;
                    case ("date"):
                        for (Task currentTask : arrayOfTasks) {
                            if (Integer.parseInt(arguments[3]) == currentTask.getGroupNumber() &&
                                    Integer.parseInt(arguments[4]) == currentTask.getListNumber() &&
                                    Integer.parseInt(arguments[5]) == currentTask.getTaskNumber()) {
                                try {
                                    currentTask.setDateOfEnd(new SimpleDateFormat("dd/MM/yyyy").parse(arguments[6]));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        break;
                }
            }
            sendMessage.setText("Вы успешно отредактировали задание!");
            break;
            case ("list"): {
                switch (arguments[2]) {
                    case ("name"):
                        for (TaskList currentList : arrayOfTaskLists) {
                            if (Integer.parseInt(arguments[3]) == currentList.getGroupNumber() &&
                                    Integer.parseInt(arguments[4]) == currentList.getTaskListNumber()) {
                                currentList.setNameTaskList(arguments[5]);
                            }
                        }
                        sendMessage.setText("Вы успешно отредактировали список!");
                        break;
                }
            }
            break;
            case ("group"): {
                switch (arguments[2]) {
                    case ("name"):
                        for (GroupOfTask currentGroup : arrayGroupOfTasks) {
                            if (Integer.parseInt(arguments[3]) == currentGroup.getGroupNumber()) {
                                currentGroup.setNameGroup(arguments[4]);
                            }
                        }
                        sendMessage.setText("Вы успешно отредактировали группу!");
                        break;
                }
            }
            break;
        }
    }
}
