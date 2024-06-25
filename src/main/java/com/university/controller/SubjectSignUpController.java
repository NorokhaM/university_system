package com.university.controller;

import com.university.db.DBDriver;
import com.university.model.SubjectModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/subject-sign-up/*")
public class SubjectSignUpController extends HttpServlet {

    private static final String INSERT_QUERY="INSERT INTO student_subjects (subject_id, student_id)\n" +
            "SELECT sub.id, s.id\n" +
            "FROM students s, subjects sub\n" +
            "WHERE s.email = ? AND sub.name = ?";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email= (String) session.getAttribute("email");
        String name=request.getParameter("subjectName");
        if (SubjectModel.getInstance().isRegistered(email,name)){
            session.setAttribute("message", "You are already registered for this subject");
            response.sendRedirect("subjects.jsp");
            return;
        }
        try(Connection connection = DBDriver.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            response.sendRedirect("student.jsp");
        }
        catch (SQLException e){
            request.getRequestDispatcher("login-failed.jsp").forward(request, response);
        }

    }
}
