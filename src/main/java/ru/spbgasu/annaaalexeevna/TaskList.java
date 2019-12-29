package ru.spbgasu.annaaalexeevna;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Integer taskListNumber;
    private String nameTaskList;
    private Integer groupNumber;

    public TaskList(Integer groupNumber, Integer taskListNumber,  String nameTaskList) {
        this.taskListNumber = taskListNumber;
        this.nameTaskList = nameTaskList;
        this.groupNumber = groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {

        this.groupNumber = groupNumber;
    }

    public void setTaskListNumber(Integer taskListNumber) {

        this.taskListNumber = taskListNumber;
    }

    public void setNameTaskList(String nameTaskList) {

        this.nameTaskList = nameTaskList;
    }

    Integer getGroupNumber() {
        return groupNumber;
    }

    Integer getTaskListNumber() {
        return taskListNumber;
    }

    String getNameTaskList() {
        return nameTaskList;
    }


}