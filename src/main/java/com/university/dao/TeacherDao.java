package com.university.dao;

import com.university.entity.Teacher;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TeacherDao {
    void addTeacher(HttpServletRequest request);
    boolean checkTeacher(HttpServletRequest request);
    List<Teacher> getTeacherByEmail(HttpServletRequest request);
    void updateTeacher(HttpServletRequest request);
//    void removeTeacher();
//    void updateTeacher();
//    void getTeacherByName();
//    void getAllTeachers();
}
