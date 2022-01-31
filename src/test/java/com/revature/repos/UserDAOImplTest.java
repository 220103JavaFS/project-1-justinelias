package com.revature.repos;

import com.revature.models.User;
import com.revature.models.UserRole;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOImplTest {

    private static UserDAO userDAO = new UserDAOImpl();

    private static User testUser = new User(
            5,
            "UserName",
            "Passsssword123",
            "Tester",
            "McTest",
            "test@test.test",
            new UserRole(1, "Employee"));

    @Test
    @Order(1)
    void testAddUser(){
        assertTrue(userDAO.addUser(testUser));
    }

    @Test
    @Order(2)
    void testGetUserByIdSuccess(){
        assertEquals(testUser, userDAO.getUserById(testUser.getErsUsersId()));
    }

    @Test
    @Order(3)
    void testGetUserByIdFail(){
        assertNull(userDAO.getUserById(-5));
    }

    @Test
    @Order(4)
    void testGetUserByUsernameSuccess(){
        assertEquals(testUser, userDAO.getUserByUsername(testUser.getErsUsername()));
    }

    @Test
    @Order(5)
    void testGetUserByUsernameFail(){
        assertNull(userDAO.getUserByUsername("Userrrrrrrrname"));
    }




}
