package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServiceTest {

    private LoginService testInstance;

    @Mock
    private UserDAO mockedDAO;

    private User testUser = new User();


    @BeforeEach
    public void setUp(){
        testUser = new  User(
                1,
                "UserName",
                "$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES",
                "Blake",
                "Jones",
                "test@test.test",
                new UserRole(1, "Employee"));
        MockitoAnnotations.openMocks(this);
        testInstance = new LoginService(mockedDAO);
        Mockito.when(mockedDAO.getUserByUsername("UserName")).thenReturn(testUser);

    }

    @Test
    public void testLoginSuccess(){
        assertTrue(testInstance.login("UserName", "PassSw0Rd555"));
    }

    @Test
    public void testLoginFailUsername(){
        assertFalse(testInstance.login("NotUserName", "PassSw0Rd555"));
    }

    @Test
    public void testLoginFailPassword(){
        assertFalse(testInstance.login("UserName", "Notpassword"));

    }

    @Test
    public void testLoginFailBoth(){
        assertFalse(testInstance.login("NotUserName", "Notpassword"));
    }

}