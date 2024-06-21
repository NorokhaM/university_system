package com.university.dao;

import com.university.entity.Student;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
//
//import java.util.List;

public interface StudentDao {
    void addStudent(HttpServletRequest request);
    List<Student> getStudentByName(HttpServletRequest request);
    boolean checkStudent(HttpServletRequest request);
//    void removeStudent();
//    void updateStudent(Student student);
//    Student getStudentByName(String name);
}
