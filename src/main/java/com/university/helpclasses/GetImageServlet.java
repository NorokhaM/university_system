package com.university.helpclasses;

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
import java.sql.ResultSet;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

    private static final String SELECT_QUERY="SELECT * FROM users WHERE email=?";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        try(Connection con= DBDriver.getInstance().getConnection()){
            String email = (String) session.getAttribute("email");
            PreparedStatement preparedStatement=con.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, email);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()){
                byte[] imgData = rs.getBytes("image");
                response.setContentType("image/jpeg");
                response.setContentLength(imgData.length);
                response.getOutputStream().write(imgData);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
