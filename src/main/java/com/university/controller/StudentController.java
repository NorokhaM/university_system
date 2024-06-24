package com.university.controller;

import com.university.dao.StudentDao;
import com.university.db.DBDriver;
import com.university.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController implements StudentDao {
    private static final String INSERT_QUERY = "INSERT INTO students (firstName, lastName, address, email, age, phone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM students WHERE email=?";
    private static final String UPDATE_QUERY= "UPDATE students SET firstName=?, lastName=?, address=?, phone=?, age=? WHERE email=?";
    private static StudentController instance = null;

    public static StudentController getInstance() {
        if (instance == null) {
            synchronized (StudentController.class) {
                if (instance == null) {
                    instance = new StudentController();
                }
            }
        }
        return instance;
    }
    @Override
    public void addStudent(HttpServletRequest request) {
        Student student= new Student();
        student.setStudentAge(Integer.parseInt(request.getParameter("age")))
                .setStudentEmail(request.getParameter("email"))
                .setStudentFirstName(request.getParameter("firstName"))
                .setStudentLastName(request.getParameter("lastName"))
                .setStudentPhone(request.getParameter("phone"))
                .setStudentAddress(request.getParameter("address"));
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setString(6, student.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getStudentByEmail(HttpServletRequest request) {
        List<Student> students = new ArrayList<>();
        HttpSession session = request.getSession(false);
        try(Connection connection = DBDriver.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, session.getAttribute("email").toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Student student = new Student();
                student.setStudentFirstName(resultSet.getString("firstName"))
                        .setStudentLastName(resultSet.getString("lastName"))
                        .setStudentAddress(resultSet.getString("address"))
                        .setStudentEmail(resultSet.getString("email"))
                        .setStudentAge(resultSet.getInt("age"))
                        .setStudentPhone(resultSet.getString("phone"));
                students.add(student);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean checkStudent(HttpServletRequest request) {
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
    public void updateStudent(HttpServletRequest request) {
        Student student = new Student();
        student.setStudentFirstName(request.getParameter("firstName"))
                .setStudentLastName(request.getParameter("lastName"))
                .setStudentAddress(request.getParameter("address"))
                .setStudentPhone(request.getParameter("phone"))
                .setStudentAge(Integer.parseInt(request.getParameter("age")));
        try(Connection connection = DBDriver.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setString(6, request.getSession().getAttribute("email").toString());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

