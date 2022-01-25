package com.revature.services;

import com.revature.models.User;

public class LoginService {

    UserDAO userDAO = new UserDAO;

    public LoginService() {
    }

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        return false;
    }
}
