package com.university.controller;


import com.university.db.DBDriver;
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

@WebServlet("/set-mark")
public class MarkController extends HttpServlet {

    private static final String INSERT_MARK_QUERY = "UPDATE student_subjects SET mark = ? WHERE student_id = ? AND subject_id = ?";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mark = Integer.parseInt(request.getParameter("mark"));
        HttpSession session=request.getSession();
        if (mark<1 || mark>10){
            session.setAttribute("error", "Mark should be between 1 and 10");
            response.sendRedirect("teacher-students.jsp");
        }
        else {
            String email = request.getParameter("email");
            int subjectId = TeacherController.getInstance().getTeachersSubjectIdByEmail(request.getSession().getAttribute("email").toString());
            int studentId = StudentController.getInstance().getStudentIdByEmail(email);
            try (Connection connection = DBDriver.getInstance().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARK_QUERY);
                preparedStatement.setInt(1, mark);
                preparedStatement.setInt(2, studentId);
                preparedStatement.setInt(3, subjectId);
                preparedStatement.executeUpdate();
                request.getRequestDispatcher("teacher-students.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


