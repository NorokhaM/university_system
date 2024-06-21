package com.university.controller;

import java.io.*;
import java.sql.*;

import com.university.db.DBDriver;
import com.university.helpclasses.RoleDispatcher;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {




    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.removeAttribute("email");
        session.removeAttribute("role");
        session.invalidate();
        response.sendRedirect("index.jsp");

    }
}
