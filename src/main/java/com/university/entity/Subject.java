package com.university.entity;

import com.university.model.SubjectModel;

public class Subject {

    private String name;
    private String description;
    private String firstNameTeacher;
    private String lastNameTeacher;
    private int mark;

    public Subject setSubjectName(String name) {
        this.name = name;
        return this;
    }

    public Subject setSubjectDescription(String description) {
        this.description = description;
        return this;
    }

    public Subject setSubjectTeacherFirstName(String firstNameTeacher) {
        this.firstNameTeacher=firstNameTeacher;
        return this;
    }

    public Subject setSubjectTeacherLastName(String lastNameTeacher) {
        this.lastNameTeacher=lastNameTeacher;
        return this;
    }

    public Subject setMark(int mark) {
        this.mark = mark;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFirstNameTeacher(){
        return firstNameTeacher;
    }

    public String getLastNameTeacher(){
        return lastNameTeacher;
    }

    public int getMark() {
        return mark;
    }

}
