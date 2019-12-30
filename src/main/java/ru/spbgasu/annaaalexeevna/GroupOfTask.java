package ru.spbgasu.annaaalexeevna;

import java.util.ArrayList;

public class GroupOfTask {
    private  int groupNumber;
    private String nameGroup;

    public GroupOfTask (int groupNumber,String nameGroup) {
        this.groupNumber = groupNumber;
        this.nameGroup = nameGroup;
    }

    public void setGroupNumber(int groupNumber) {

        this.groupNumber = groupNumber;
    }

    public void setNameGroup(String nameGroup) {

        this.nameGroup = nameGroup;
    }

    int getGroupNumber() {
        return groupNumber;
    }

    String getNameGroup() {
        return nameGroup;
    }
}
