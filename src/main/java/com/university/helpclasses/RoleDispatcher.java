package com.university.helpclasses;

import com.university.dao.RoleDao;

public class RoleDispatcher implements RoleDao {

    private static RoleDispatcher instance;

    private RoleDispatcher(){
    }

    public static RoleDispatcher getInstance(){
        if(instance == null){
            instance = new RoleDispatcher();
        }
        return instance;
    }
    @Override
    public String getRole(String role){
        switch(role){
            case "admin":
                return "admin.jsp";
            case "Teacher":
                return "teacher.jsp";
            case "Student":
                return "student.jsp";
            default:
                return "unknown";
        }
    }


}
