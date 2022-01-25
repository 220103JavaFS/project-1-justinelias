package com.revature.services;

import com.revature.models.UserRole;
import com.revature.repos.UserRoleDAO;
import com.revature.repos.UserRoleDAOImpl;

public class UserRoleService {

    private UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

    public UserRole getRoleById(int id) {
        return userRoleDAO.getRoleById(id);
    }
    public boolean addRole(UserRole userRole){
        return false;
    }
}
