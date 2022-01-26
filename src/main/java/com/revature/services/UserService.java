package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAOImpl;
import com.revature.repos.UserDAO;

public class UserService {
//    private UserDAO userDao = new UserDAOImpl();

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User getUserByUsername(String ersUsername) {
        return userDao.getUserByUsername(ersUsername);
    }

    public boolean addUser(User user){
        return false;
    }
}
