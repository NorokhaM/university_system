package com.university.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface UserDao {

    void addUser(HttpServletRequest request) throws ServletException, IOException;
    boolean checkUser(HttpServletRequest request);

}
