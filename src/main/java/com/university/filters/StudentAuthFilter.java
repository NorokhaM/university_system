package com.university.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/teacher.jsp", "/teacher-students.jsp", "/teacher-profile.jsp", "/set-mark"})
public class StudentAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");
        if (role.equals("Student")){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("student.jsp");
        }
        else {
            chain.doFilter(request, response);
        }
    }

}
