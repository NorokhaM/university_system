package com.university.entity;

import com.university.model.SubjectModel;

public class Subject {

    private String name;
    private String description;
    private String firstNameTeacher;
    private String lastNameTeacher;

    private static Subject instance = null;

    public static Subject getInstance(){
        if (instance == null) {
            synchronized (Subject.class) {
                if (instance == null) {
                    instance = new Subject();
                }
            }
        }
        return instance;
    }

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
}
