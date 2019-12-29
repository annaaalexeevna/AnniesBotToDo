package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;

public class Task {
    private Integer groupNumber;
    private Integer listNumber;
    private Integer taskNumber;
    private String nameTask;
    private String descriptionTask;
    private User user;
    private Date dateOfEnd;
    private Boolean isReady;


    public Task(Integer groupNumber, Integer listNumber, Integer taskNumber, String nameTask, String descriptionTask, User user, Date dateOfEnd, boolean isReady) {
        this.taskNumber = taskNumber;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.user = user;
        this.dateOfEnd = dateOfEnd;
        this.isReady = isReady;
        this.listNumber = listNumber;
        this.groupNumber = groupNumber;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public void setIsReady(Boolean isReady) {
        this.isReady = isReady;
    }

    Integer getGroupNumber() {
        return groupNumber;
    }

    Integer getListNumber() {
        return listNumber;
    }

    Integer getTaskNumber() {
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

    Boolean getIsReady() {
        return isReady;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setListNumber(Integer listNumber) {
        this.listNumber = listNumber;
    }
}
