package com.university.controller;

import com.university.model.SubjectModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateProfile")
public class UpdateProfileController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = request.getSession().getAttribute("role").toString();
        if (role.equals("Teacher")) {
            TeacherController.getInstance().updateTeacher(request);
            response.sendRedirect("profile");
        }
        if (role.equals("Student")) {
            StudentController.getInstance().updateStudent(request);
            response.sendRedirect("profile");
        }


    }
}
