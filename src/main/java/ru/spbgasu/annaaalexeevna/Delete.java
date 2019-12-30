package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;

public class Delete extends BotCommand {

    private final Main main;
    private final SendMessage sendMessage;
    ArrayList<Task> arrayOfTasks;
    ArrayList<TaskList> arrayOfTaskLists;
    ArrayList<GroupOfTask> arrayGroupOfTasks;

    public Delete(String commandIdentifier, String description, ArrayList<Task> arrayOfTasks, ArrayList<TaskList> arrayOfTaskLists, ArrayList<GroupOfTask> arrayGroupOfTasks, Main main) {
        super(commandIdentifier, description);
        this.arrayOfTasks = arrayOfTasks;
        this.arrayOfTaskLists = arrayOfTaskLists;
        this.arrayGroupOfTasks = arrayGroupOfTasks;
        this.main = main;
        sendMessage = new SendMessage();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        switch (arguments[1]) {
            case "task":
                for (Task currentTask : arrayOfTasks) {
                    if (currentTask.getGroupNumber() == Integer.parseInt(arguments[2]) &&
                            currentTask.getListNumber() == Integer.parseInt(arguments[3]) &&
                            currentTask.getTaskNumber() == Integer.parseInt(arguments[4])) {
                        arrayOfTasks.remove(currentTask);
                        restoreNumberTask(currentTask.getTaskNumber(), arrayOfTasks.size(), arrayOfTasks);
                        sendMessage.setText("Вы успешно удалили задание!");
                    }
                }
                break;
            case "list":
                for (TaskList currentList : arrayOfTaskLists) {
                    if (currentList.getGroupNumber() == Integer.parseInt(arguments[2]) &&
                            currentList.getTaskListNumber() == Integer.parseInt(arguments[3])) {
                        arrayOfTaskLists.remove(currentList);
                        restoreNumberTaskList(currentList.getTaskListNumber(), arrayOfTaskLists.size(), arrayOfTaskLists);
                    }
                }
                for (Task currentTask : arrayOfTasks) {
                    if (currentTask.getGroupNumber() == Integer.parseInt(arguments[2]) &&
                            currentTask.getListNumber() == Integer.parseInt(arguments[3])) {
                        arrayOfTasks.remove(currentTask);
                    }
                    if (currentTask.getListNumber() > Integer.parseInt(arguments[2])) {
                        currentTask.setListNumber(currentTask.getListNumber() - 1);
                    }
                }
                sendMessage.setText("Вы успешно удалили список и задания, которые содержались в нем!");
                break;
            case "group":
                for (GroupOfTask currentGroup : arrayGroupOfTasks) {
                    if (currentGroup.getGroupNumber() == Integer.parseInt(arguments[2])) {
                        arrayGroupOfTasks.remove(currentGroup);
                        restoreNumberGroupOfTask(currentGroup.getGroupNumber(), arrayOfTasks.size(), arrayGroupOfTasks);
                    }
                }
                for (TaskList currentList : arrayOfTaskLists) {
                    if (currentList.getGroupNumber() == Integer.parseInt(arguments[2]) &&
                            currentList.getTaskListNumber() == Integer.parseInt(arguments[3])) {
                        arrayOfTaskLists.remove(currentList);
                    }
                    if (currentList.getGroupNumber() > Integer.parseInt(arguments[2])) {
                        currentList.setGroupNumber(currentList.getGroupNumber() - 1);
                    }
                }
                for (Task currentTask : arrayOfTasks) {
                    if (currentTask.getGroupNumber() == Integer.parseInt(arguments[2]) &&
                            currentTask.getListNumber() == Integer.parseInt(arguments[3]) &&
                            currentTask.getTaskNumber() == Integer.parseInt(arguments[4])) {
                        arrayOfTasks.remove(currentTask);
                    }
                    if (currentTask.getGroupNumber() > Integer.parseInt(arguments[2])) {
                        currentTask.setGroupNumber(currentTask.getGroupNumber() - 1);
                    }
                }
                sendMessage.setText("Вы успешно удалили группу и все списки и задания, которые содержались в ней!");
                break;
        }
    }

    public void restoreNumberTask(int numberStart, int numberLast, ArrayList<Task> arrayOfTasks) {
        for (int i = numberStart + 1; i < numberLast; i++) {
            arrayOfTasks.get(i).setTaskNumber(arrayOfTasks.get(i).getTaskNumber() - 1);
        }
    }

    public void restoreNumberTaskList(int numberStart, int numberLast, ArrayList<TaskList> arrayOfTaskLists) {
        for (int i = numberStart + 1; i < numberLast; i++) {
            arrayOfTaskLists.get(i).setTaskListNumber(arrayOfTaskLists.get(i).getTaskListNumber() - 1);
        }
    }

    public void restoreNumberGroupOfTask(int numberStart, int numberLast, ArrayList<GroupOfTask> arrayGroupOfTasks) {
        for (int i = numberStart + 1; i < numberLast; i++) {
            arrayGroupOfTasks.get(i).setGroupNumber(arrayGroupOfTasks.get(i).getGroupNumber() - 1);
        }
    }
}
