package com.university.model;

import com.university.dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;

public class SubjectModel implements SubjectDao {

    private static final String INSERT_QUERY = "INSERT INTO teacher_subject (teacher_id, subject_id) VALUES (?, ?)";
    private static final String SELECT_TEACHER_QUERY = "SELECT * FROM teachers WHERE id = ?";
    private static final String SELECT_SUBJECT_QUERY = "SELECT * FROM subjects WHERE id = ?";
    private static SubjectModel instance = null;

    public static SubjectModel getInstance() {
        if (instance == null) {
            synchronized (SubjectModel.class) {
                if (instance == null) {
                    instance = new SubjectModel();
                }
            }
        }
        return instance;
    }

    @Override
    public void updateTeacherSubject(HttpServletRequest request) {
        String subject=request.getParameter("subject");
    }

}
