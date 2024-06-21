package com.university.controller;

import com.university.dao.UserDao;
import com.university.db.DBDriver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.*;

public class UserController implements UserDao {
    private static UserController instance = null;

    private static final String INSERT_QUERY = "INSERT INTO users (email, password, role, image) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE email = ?";
    public static UserController getInstance() {
        if (instance == null) {
            synchronized (UserController.class) {
                if (instance == null) {
                    instance = new UserController();
                }
            }
        }
        return instance;
    }
    @Override
    public void addUser(HttpServletRequest request) throws ServletException, IOException{
        try (Connection connection = DBDriver.getInstance().getConnection()) {
            Part filePart = request.getPart("image");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, request.getParameter("email").toString());
            preparedStatement.setString(2, request.getParameter("password").toString());
            preparedStatement.setString(3, request.getParameter("role").toString());
            preparedStatement.setBinaryStream(4, filePart.getInputStream());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        try(Connection connection = DBDriver.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, email);
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


}
