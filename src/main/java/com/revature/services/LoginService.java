package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class LoginService {

    private UserDAO userDAO;

    public LoginService() {
        this.userDAO = new UserDAOImpl();
    }

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if(user != null) {
            return BCrypt.checkpw(password, user.getErsPassword());
        }else{
            return false;
        }
    }
}
