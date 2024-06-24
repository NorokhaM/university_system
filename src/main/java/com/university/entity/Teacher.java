package com.university.entity;

public class Teacher {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private int age;
    private String subject;
    private int subjectId;
    private static Teacher instance = null;

    public static Teacher getInstance(){
        if (instance == null) {
            synchronized (Teacher.class) {
                if (instance == null) {
                    instance = new Teacher();
                }
            }
        }
        return instance;
    }

    public Teacher setTeacherFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Teacher setTeacherLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Teacher setTeacherAddress(String address) {
        this.address = address;
        return this;
    }

    public Teacher setTeacherEmail(String email) {
        this.email = email;
        return this;
    }

    public Teacher setTeacherPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Teacher setTeacherAge(int age) {
        this.age = age;
        return this;
    }

    public Teacher setTeacherSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Teacher setSubjectId(int subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getSubject() {
        return subject;
    }

    public String getAddress() {
        return address;
    }

    public int getSubjectId() {
        return subjectId;
    }



}
