package com.revature.repos;

import com.revature.models.User;
import com.revature.models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOImplTest {

    private static UserDAO userDAO = new UserDAOImpl();

    private static User testUser = new User(
            12345,
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
    void testGetUserById(){
        assertEquals(testUser, userDAO.getUserById(testUser.getErsUsersId()));
    }

    @Test
    @Order(3)
    void testGetUserByUsername(){
        assertEquals(testUser, userDAO.getUserByUsername(testUser.getErsUsername()));
    }





}
