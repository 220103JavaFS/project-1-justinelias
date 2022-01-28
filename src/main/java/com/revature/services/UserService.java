package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAOImpl;
import com.revature.repos.UserDAO;

public class UserService {
    private UserDAO userDao = new UserDAOImpl();

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public User getUserById(int id) {
        if (id > 0) {
            return userDao.getUserById(id);
        } else {
            return null;
        }
    }

    public User getUserByUsername(String ersUsername) {
        if (ersUsername!=null){
            return userDao.getUserByUsername(ersUsername);
        }else{
            return null;
        }
    }

    public boolean addUser(User user) {
        if (user.getErsUsername() != null) {
            return userDao.addUser(user);
        } else {
            return false;
        }
    }
}
