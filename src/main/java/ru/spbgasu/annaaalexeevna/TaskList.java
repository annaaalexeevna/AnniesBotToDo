package ru.spbgasu.annaaalexeevna;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private int taskListNumber;
    private String nameTaskList;
    private int groupNumber;

    public TaskList(int groupNumber, int taskListNumber, String nameTaskList) {
        this.groupNumber = groupNumber;
        this.taskListNumber = taskListNumber;
        this.nameTaskList = nameTaskList;
    }

    public void setGroupNumber(int groupNumber) {

        this.groupNumber = groupNumber;
    }

    public void setTaskListNumber(int taskListNumber) {

        this.taskListNumber = taskListNumber;
    }

    public void setNameTaskList(String nameTaskList) {

        this.nameTaskList = nameTaskList;
    }

    int getGroupNumber() {
        return groupNumber;
    }

    int getTaskListNumber() {
        return taskListNumber;
    }

    String getNameTaskList() {
        return nameTaskList;
    }


}