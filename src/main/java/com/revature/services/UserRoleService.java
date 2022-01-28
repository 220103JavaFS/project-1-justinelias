package com.revature.services;

import com.revature.models.UserRole;
import com.revature.repos.UserRoleDAO;
import com.revature.repos.UserRoleDAOImpl;

public class UserRoleService {
    private UserRoleDAO userRoleDAO;

    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public UserRoleService() {
       this.userRoleDAO = new UserRoleDAOImpl();
    }



    public UserRole getRoleById(int id) {
        if(id>0){
            return userRoleDAO.getRoleById(id);
        }else{
            return null;
        }
    }

    public boolean addRole(UserRole userRole){
        if(userRole.getErsUserRoleId() > 0){
            return userRoleDAO.addRole(userRole);
        }else{
            return false;
        }
    }
}
