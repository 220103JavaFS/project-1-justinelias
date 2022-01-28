package com.revature.repos;

import com.revature.models.User;

public interface UserDAO {
    public User getUserById(int id);
    public User getUserByUsername(String ersUsername);
    public boolean addUser(User user);
}
