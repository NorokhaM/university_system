package com.university.dao;

import com.university.entity.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> getAllSubjects();
    List<Subject> getSubjectByStudentEmail(String email);
    int getSubjectIdByName(String subjectName);
    boolean isRegistered(String email, String subjectName);
}
