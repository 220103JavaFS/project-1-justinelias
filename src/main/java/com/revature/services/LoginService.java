package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class LoginService {

    UserDAO userDAO = new UserDAOImpl();

    public LoginService() {
    }

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        return false;
    }
}
