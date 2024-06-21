package com.university.db;

import java.sql.*;

public class DBDriver {
    private final String URL="jdbc:postgresql://localhost:5432/university_system";
    private final String USER="postgres";
    private final String PASSWORD="admin";

    private static DBDriver instance;

    public static DBDriver getInstance(){
        if(instance == null){
            instance = new DBDriver();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
