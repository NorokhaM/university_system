package com.university.controller;

import java.io.*;
import java.sql.*;

import com.university.db.DBDriver;
import com.university.helpclasses.RoleDispatcher;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class SignInController extends HttpServlet {

    private static final String SELECT_QUERY = "SELECT * FROM users WHERE email = ? AND password = ?";


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (session.getAttribute("email") == null || session.getAttribute("role") == null) {
            try(Connection connection = DBDriver.getInstance().getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String role= RoleDispatcher.getInstance().getRole(resultSet.getString("role"));
                    session.setAttribute("role", resultSet.getString("role"));
                    session.setAttribute("email", email);
                    response.sendRedirect(role);
                }
                else{
                    request.getRequestDispatcher("login-failed.jsp").forward(request, response);
                }
            }
            catch (SQLException e){
                request.getRequestDispatcher("login-failed.jsp").forward(request, response);
            }
        }
    }
}
