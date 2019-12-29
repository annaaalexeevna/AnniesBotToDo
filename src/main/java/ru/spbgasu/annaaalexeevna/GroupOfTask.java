package ru.spbgasu.annaaalexeevna;

import java.util.ArrayList;

public class GroupOfTask {
    private  Integer groupNumber;
    private String nameGroup;

    public GroupOfTask (Integer groupNumber,String nameGroup) {
        this.groupNumber = groupNumber;
        this.nameGroup = nameGroup;
    }

    public void setGroupNumber(Integer groupNumber) {

        this.groupNumber = groupNumber;
    }

    public void setNameGroup(String nameGroup) {

        this.nameGroup = nameGroup;
    }

    Integer getGroupNumber() {
        return groupNumber;
    }

    String getNameGroup() {
        return nameGroup;
    }
}
