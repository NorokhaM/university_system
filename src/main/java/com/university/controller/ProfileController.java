package com.university.controller;

import java.io.*;
import java.sql.*;

import com.university.dao.RoleDao;
import com.university.db.DBDriver;
import com.university.helpclasses.RoleDispatcher;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/profile")
public class ProfileController extends HttpServlet implements RoleDao {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String role=(String) session.getAttribute("role");
        request.getRequestDispatcher(getRole(role)).forward(request, response);
    }

    @Override
    public String getRole(String role){
        switch (role) {
            case "Student":
                return "student-profile.jsp";
            case "Teacher":
                return "teacher-profile.jsp";
            case "Admin":
                return "admin-profile.jsp";
            default:
                return "unknown";
        }
    }
}
