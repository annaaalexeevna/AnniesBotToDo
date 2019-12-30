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
import java.util.List;

public class ClassOfArrayLists {

    List<Task> arrayOfTasks;
    List<TaskList> arrayOfTaskLists;
    List<GroupOfTask> arrayGroupOfTasks;

    public ClassOfArrayLists() {
        this.arrayOfTasks = new ArrayList<Task>();
        this.arrayOfTaskLists = new ArrayList<TaskList>();
        this.arrayGroupOfTasks = new ArrayList<GroupOfTask>();
    }

    public void addTask(Task newTask) {
        arrayOfTasks.add(newTask);
    }

    public void addList(TaskList newTaskList) {
        arrayOfTaskLists.add(newTaskList);
    }

    public void addGroup(GroupOfTask newGroup) {
        arrayGroupOfTasks.add(newGroup);
    }

    public void deleteTask(int group, int list, int task) {
        for (Task currentTask : arrayOfTasks) {
            if (currentTask.getGroupNumber() == group &&
                    currentTask.getListNumber() == list &&
                    currentTask.getTaskNumber() == task) {
                arrayOfTasks.remove(currentTask);
                restoreNumberTask(currentTask.getTaskNumber(), arrayOfTasks.size(), arrayOfTasks);
            }
        }
    }

    public void deleteList(int group, int list) {
        for (TaskList currentList : arrayOfTaskLists) {
            if (currentList.getGroupNumber() == group &&
                    currentList.getTaskListNumber() == list) {
                arrayOfTaskLists.remove(currentList);
                restoreNumberTaskList(currentList.getTaskListNumber(), arrayOfTaskLists.size(), arrayOfTaskLists);
            }
        }
        for (Task currentTask : arrayOfTasks) {
            if (currentTask.getGroupNumber() == group &&
                    currentTask.getListNumber() == list) {
                arrayOfTasks.remove(currentTask);
            }
            if (currentTask.getListNumber() > list) {
                currentTask.setListNumber(currentTask.getListNumber() - 1);
            }
        }
    }

    public void deleteGroup(int group) {
        for (GroupOfTask currentGroup : arrayGroupOfTasks) {
            if (currentGroup.getGroupNumber() == group) {
                arrayGroupOfTasks.remove(currentGroup);
                restoreNumberGroupOfTask(currentGroup.getGroupNumber(), arrayOfTasks.size(), arrayGroupOfTasks);
            }
        }
        for (TaskList currentList : arrayOfTaskLists) {
            if (currentList.getGroupNumber() == group) {
                arrayOfTaskLists.remove(currentList);
            }
            if (currentList.getGroupNumber() > group) {
                currentList.setGroupNumber(currentList.getGroupNumber() - 1);
            }
        }
        for (Task currentTask : arrayOfTasks) {
            if (currentTask.getGroupNumber() == group) {
                arrayOfTasks.remove(currentTask);
            }
            if (currentTask.getGroupNumber() > group) {
                currentTask.setGroupNumber(currentTask.getGroupNumber() - 1);
            }
        }
    }

    public void editTask(String fieldToEdit, int group, int list, int task, String newValue) {
        switch (fieldToEdit) {
            case ("name"):
                for (Task currentTask : arrayOfTasks) {
                    if (group == currentTask.getGroupNumber() && list == currentTask.getListNumber() &&
                            task == currentTask.getTaskNumber()) {
                        currentTask.setNameTask(newValue);
                    }
                }
                break;
            case ("description"):
                for (Task currentTask : arrayOfTasks) {
                    if (group == currentTask.getGroupNumber() && list == currentTask.getListNumber() &&
                            task == currentTask.getTaskNumber()) {
                        currentTask.setDescriptionTask(newValue);
                    }
                }
                break;
            case ("date"):
                for (Task currentTask : arrayOfTasks) {
                    if (group == currentTask.getGroupNumber() && list == currentTask.getListNumber() &&
                            task == currentTask.getTaskNumber()) {
                        try {
                            currentTask.setDateOfEnd(new SimpleDateFormat("dd/MM/yyyy").parse(newValue));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

    public void editList(String fieldToEdit, int group, int list, String newValue) {
        switch (fieldToEdit) {
            case ("name"):
                for (TaskList currentList : arrayOfTaskLists) {
                    if (group == currentList.getGroupNumber() && list == currentList.getTaskListNumber()) {
                        currentList.setNameTaskList(newValue);
                    }
                }
                break;
        }
    }

    public void editGroup(String fieldToEdit, int group, String newValue) {
        switch (fieldToEdit) {
            case ("name"):
                for (GroupOfTask currentGroup : arrayGroupOfTasks) {
                    if (group == currentGroup.getGroupNumber()) {
                        currentGroup.setNameGroup(newValue);
                    }
                }
                break;
        }
    }

    public String show() {
        String finalString = "";
        for (GroupOfTask currentGroup : arrayGroupOfTasks) {
            finalString += currentGroup.getGroupNumber() + ". " + currentGroup.getNameGroup() + '\n';
            for (TaskList currentTaskList : arrayOfTaskLists) {
                if (currentGroup.getGroupNumber() == currentTaskList.getGroupNumber()) {
                    finalString += '\t' + currentTaskList.getTaskListNumber() + ". " + currentTaskList.getNameTaskList() + '\n';
                    for (Task currentTask : arrayOfTasks) {
                        if (currentTaskList.getTaskListNumber() == currentTask.getListNumber()) {
                            finalString += '\t' + '\t' + currentTask.getTaskNumber() + ". " + currentTask.getNameTask() + (currentTask.getIsReady() ? " - выполнено" : " - не выполнено") + '\n';
                        }
                    }
                }
            }
        }
        return finalString;
    }

    public void markReady(int group, int list, int task) {
        for (Task currentTask : arrayOfTasks) {
            if (group == currentTask.getGroupNumber() && list == currentTask.getListNumber() && task == currentTask.getTaskNumber()) {
                currentTask.setIsReady(true);
            }
        }
    }

    public void restoreNumberTask(int numberStart, int numberLast, List<Task> arrayOfTasks) {
        for (int i = numberStart + 1; i < numberLast; i++) {
            arrayOfTasks.get(i).setTaskNumber(arrayOfTasks.get(i).getTaskNumber() - 1);
        }
    }

    public void restoreNumberTaskList(int numberStart, int numberLast, List<TaskList> arrayOfTaskLists) {
        for (int i = numberStart + 1; i < numberLast; i++) {
            arrayOfTaskLists.get(i).setTaskListNumber(arrayOfTaskLists.get(i).getTaskListNumber() - 1);
        }
    }

    public void restoreNumberGroupOfTask(int numberStart, int numberLast, List<GroupOfTask> arrayGroupOfTasks) {
        for (int i = numberStart + 1; i < numberLast; i++) {
            arrayGroupOfTasks.get(i).setGroupNumber(arrayGroupOfTasks.get(i).getGroupNumber() - 1);
        }
    }
}
