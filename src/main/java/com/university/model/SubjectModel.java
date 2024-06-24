package com.university.model;

import com.university.dao.SubjectDao;
import com.university.db.DBDriver;
import com.university.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectModel implements SubjectDao {


    private static final String SELECT_SUBJECT_QUERY = "SELECT subjects.name, subjects.description, teachers.firstname, teachers.lastname FROM subjects JOIN teachers ON subjects.id=teachers.subject_id";
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

}
