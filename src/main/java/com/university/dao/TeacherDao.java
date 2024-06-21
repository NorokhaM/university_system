package com.university.dao;

import jakarta.servlet.http.HttpServletRequest;

public interface TeacherDao {
    void addTeacher(HttpServletRequest request);
    boolean checkTeacher(HttpServletRequest request);
//    void removeTeacher();
//    void updateTeacher();
//    void getTeacherByName();
//    void getAllTeachers();
}
