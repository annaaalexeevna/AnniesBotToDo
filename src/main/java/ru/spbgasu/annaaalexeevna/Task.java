package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;

public class Task {
    private int groupNumber;
    private int listNumber;
    private int taskNumber;
    private String nameTask;
    private String descriptionTask;
    private User user;
    private Date dateOfEnd;
    private boolean isReady;


    public Task(int groupNumber, int listNumber, int taskNumber, String nameTask, String descriptionTask, User user, Date dateOfEnd, boolean isReady) {
        this.groupNumber = groupNumber;
        this.listNumber = listNumber;
        this.taskNumber = taskNumber;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.user = user;
        this.dateOfEnd = dateOfEnd;
        this.isReady = isReady;
    }

    public void setGroupNumber(int groupNumber) {

        this.groupNumber = groupNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    int getGroupNumber() {
        return groupNumber;
    }

    int getListNumber() {
        return listNumber;
    }

    int getTaskNumber() {
        return taskNumber;
    }

    String getNameTask() {
        return nameTask;
    }

    String getDescriptionTask() {
        return descriptionTask;
    }

    User getUser() {
        return user;
    }

    Date getDateOfEnd() {
        return dateOfEnd;
    }

    boolean getIsReady() {
        return isReady;
    }

}
