package com.university.entity;

import java.util.*;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private int age;
    private List<String> listOfSubjects;

    public Student() {
        this.listOfSubjects = new ArrayList<>();
    }



    public Student setStudentFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


    public Student setStudentLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Student setStudentEmail(String email) {
        this.email = email;
        return this;
    }

    public Student setStudentPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Student setStudentAddress(String address) {
        this.address = address;
        return this;
    }

    public Student setStudentAge(int age) {
        this.age = age;
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

    public String getAddress() {
        return address;
    }


    public int getAge() {
        return age;
    }

    public List<String> getListOfSubjects() {
        return listOfSubjects;
    }

    public void addSubject(String subject) {
        listOfSubjects.add(subject);
    }
}
