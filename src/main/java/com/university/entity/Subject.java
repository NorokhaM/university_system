package com.university.entity;

public class Subject {

    private String name;
    private String description;


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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
