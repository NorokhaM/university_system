package com.university.controller;

import com.university.dao.TeacherDao;
import com.university.db.DBDriver;
import com.university.entity.Student;
import com.university.entity.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherController implements TeacherDao {
    private static final String INSERT_QUERY = "INSERT INTO teachers (firstName, lastName, address, email, phone, age, subject) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM teachers WHERE email = ?";
    private static final String UPDATE_QUERY = "UPDATE teachers SET firstName = ?, lastName = ?, address = ?, phone = ?, subject = ?, age = ?, subject_id = ? WHERE email = ?";
    private static final String SELECT_SUBJECT_QUERY = "SELECT id FROM subjects WHERE name = ?";

    private static TeacherController instance = null;

    public static TeacherController getInstance() {
        if (instance == null) {
            synchronized (TeacherController.class) {
                if (instance == null) {
                    instance = new TeacherController();
                }
            }
        }
        return instance;
    }

    @Override
    public void addTeacher(HttpServletRequest request) {
        Teacher.getInstance()
                .setTeacherFirstName(request.getParameter("firstName"))
                .setTeacherLastName(request.getParameter("lastName"))
                .setTeacherEmail(request.getParameter("email"))
                .setTeacherPhone(request.getParameter("phone"))
                .setTeacherAge(Integer.parseInt(request.getParameter("age")))
                .setTeacherSubject(request.getParameter("subject"))
                .setTeacherAddress(request.getParameter("address"));

        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, Teacher.getInstance().getFirstName());
            preparedStatement.setString(2, Teacher.getInstance().getLastName());
            preparedStatement.setString(3, Teacher.getInstance().getAddress());
            preparedStatement.setString(4, Teacher.getInstance().getEmail());
            preparedStatement.setString(5, Teacher.getInstance().getPhone());
            preparedStatement.setInt(6, Teacher.getInstance().getAge());
            preparedStatement.setString(7, Teacher.getInstance().getSubject());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkTeacher(HttpServletRequest request) {
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, request.getParameter("email"));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Teacher> getTeacherByEmail(HttpServletRequest request) {
        List<Teacher> teachers = new ArrayList<>();
        HttpSession session = request.getSession(false);
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, session.getAttribute("email").toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.getInstance();
                teacher.setTeacherFirstName(resultSet.getString("firstName"))
                        .setTeacherLastName(resultSet.getString("lastName"))
                        .setTeacherAddress(resultSet.getString("address"))
                        .setTeacherEmail(resultSet.getString("email"))
                        .setTeacherAge(resultSet.getInt("age"))
                        .setTeacherPhone(resultSet.getString("phone"))
                        .setTeacherSubject(resultSet.getString("subject"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public void updateTeacher(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age=Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String subject = request.getParameter("subject");
        int subjectId = findSubjectId(subject);
        request.getSession().setAttribute("subjectId", subjectId);
        Teacher teacher = Teacher.getInstance();
        setTeacherAttributes(teacher, firstName, lastName, phone, age, address, subject, subjectId);
        updateTeacherInDatabase(teacher, request);
    }

    private void setTeacherAttributes(Teacher teacher, String firstName, String lastName, String phone, int age, String address, String subject, int subjectId) {
        if (!firstName.isEmpty()) {
            teacher.setTeacherFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            teacher.setTeacherLastName(lastName);
        }
        if (!phone.isEmpty()) {
            teacher.setTeacherPhone(phone);
        }
        if (!address.isEmpty()) {
            teacher.setTeacherAddress(address);
        }
        if (!subject.isEmpty()) {
            teacher.setTeacherSubject(subject);
        }
        if (age != 0) {
            teacher.setTeacherAge(age);
        }
        if (subjectId != 0) {
            teacher.setSubjectId(subjectId);
        }
    }

    private void updateTeacherInDatabase(Teacher teacher, HttpServletRequest request) {
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getAddress());
            preparedStatement.setString(4, teacher.getPhone());
            preparedStatement.setString(5, teacher.getSubject());
            preparedStatement.setInt(6, teacher.getAge());
            preparedStatement.setInt(7, teacher.getSubjectId());
            preparedStatement.setString(8, request.getSession().getAttribute("email").toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int findSubjectId(String subject) {
        int subjectId = 0;
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_QUERY);
            preparedStatement.setString(1, subject);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subjectId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectId;
    }

    @Override
    public int getTeachersSubjectIdByEmail(String email) {
        int subjectId = 0;
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subjectId = resultSet.getInt("subject_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectId;
    }
}
