package com.university.controller;
import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/registration")
@MultipartConfig(maxFileSize = 16177215)
public class RegistrationController extends HttpServlet{


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkValid(request)) {
            response.sendRedirect("registration-failed.jsp");
            return;
        }
        if (request.getParameter("role").equals("Student")) {
            StudentController.getInstance().addStudent(request);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        else{
            TeacherController.getInstance().addTeacher(request);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        UserController.getInstance().addUser(request);
    }

    private boolean checkValid(HttpServletRequest request) {
        return UserController.getInstance().checkUser(request)
                || StudentController.getInstance().checkStudent(request)
                || TeacherController.getInstance().checkTeacher(request);
    }

//    private boolean checkValid(HttpServletRequest request) {
//        return request.getParameter("firstName").matches("[0-9]\\s")
//                || request.getParameter("lastName").matches("[0-9]\\s")
//                || request.getParameter("address").isEmpty()
//                || request.getParameter("email").matches("^[A-Za-z0-9+_.-]+@(.+)$")
//                || request.getParameter("password").isEmpty();
//    }

}
