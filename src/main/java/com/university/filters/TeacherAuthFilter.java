package com.university.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/student.jsp", "/subjects.jsp", "/student-subjects.jsp", "/student-profile.jsp"})
public class TeacherAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");
        if (role.equals("Teacher")){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("teacher.jsp");
        }
        else {
            chain.doFilter(request, response);
        }
    }
}
