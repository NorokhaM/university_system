package com.university.model;

import com.university.controller.StudentController;
import com.university.controller.TeacherController;
import com.university.dao.SubjectDao;
import com.university.db.DBDriver;
import com.university.entity.Student;
import com.university.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectModel implements SubjectDao {


    private static final String SELECT_SUBJECT_QUERY = "SELECT subjects.name, subjects.description, teachers.firstname, teachers.lastname FROM subjects JOIN teachers ON subjects.id=teachers.subject_id";
    private static final String SELECT_SUBJECT_ID_QUERY = "SELECT student_id, subject_id FROM student_subjects WHERE student_id=? AND subject_id=?";
    private static final String SELECT_SUBJECT_NAME_QUERY = "SELECT id FROM subjects WHERE name=?";
    private static final String SELECT_STUDENT_SUBJECTS_QUERY = "SELECT subjects.name, subjects.description, teachers.firstname, teachers.lastname, student_subjects.mark FROM subjects JOIN teachers ON subjects.id=teachers.subject_id JOIN student_subjects ON subjects.id=student_subjects.subject_id WHERE student_subjects.student_id=?";


    private static SubjectModel instance = null;

    private SubjectModel() {
    }

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
    public List<Subject> getAllSubjects(){
        List<Subject> list=new ArrayList<>();
        try (Connection conn=DBDriver.getInstance().getConnection()){
            PreparedStatement ps=conn.prepareStatement(SELECT_SUBJECT_QUERY);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Subject subject=new Subject();
                subject.setSubjectName(rs.getString("name"));
                subject.setSubjectDescription(rs.getString("description"));
                subject.setSubjectTeacherFirstName(rs.getString("firstname"));
                subject.setSubjectTeacherLastName(rs.getString("lastname"));
                list.add(subject);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getSubjectIdByName(String subjectName) {
        try (Connection conn=DBDriver.getInstance().getConnection()){
            PreparedStatement ps=conn.prepareStatement(SELECT_SUBJECT_NAME_QUERY);
            ps.setString(1, subjectName);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                return rs.getInt("id");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean isRegistered(String email, String subjectName) {
        try (Connection conn=DBDriver.getInstance().getConnection()){
            PreparedStatement ps=conn.prepareStatement(SELECT_SUBJECT_ID_QUERY);
            ps.setInt(1, StudentController.getInstance().getStudentIdByEmail(email));
            ps.setInt(2, getSubjectIdByName(subjectName));
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Subject> getSubjectByStudentEmail(String email) {
        List<Subject> subjects=new ArrayList<>();
        try (Connection conn=DBDriver.getInstance().getConnection()){
            PreparedStatement ps=conn.prepareStatement(SELECT_STUDENT_SUBJECTS_QUERY);
            ps.setInt(1, StudentController.getInstance().getStudentIdByEmail(email));
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Subject subject=new Subject();
                subject.setSubjectName(rs.getString("name"));
                subject.setSubjectDescription(rs.getString("description"));
                subject.setSubjectTeacherFirstName(rs.getString("firstname"));
                subject.setSubjectTeacherLastName(rs.getString("lastname"));
                subject.setMark(rs.getInt("mark"));
                subjects.add(subject);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return subjects;
    }
}
