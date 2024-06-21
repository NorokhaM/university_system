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

        try(Connection connection = DBDriver.getInstance().getConnection()) {
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
        try(Connection connection = DBDriver.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, request.getParameter("email"));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Teacher> getTeacherByEmail(HttpServletRequest request){
        List<Teacher> teachers = new ArrayList<>();
        HttpSession session = request.getSession(false);
        try(Connection connection = DBDriver.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, session.getAttribute("email").toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return teachers;
    }
}
