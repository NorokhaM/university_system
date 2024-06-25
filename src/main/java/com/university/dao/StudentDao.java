package com.university.dao;

import com.university.entity.Student;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
//
//import java.util.List;

public interface StudentDao {
    void addStudent(HttpServletRequest request);
    List<Student> getStudentByEmail(HttpServletRequest request);
    List<Student> getStudentsByTeacherEmail(String email);
    int getStudentIdByEmail(String email);
    boolean checkStudent(HttpServletRequest request);
    void updateStudent(HttpServletRequest request);
}
