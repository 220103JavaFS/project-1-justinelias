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
                "Password",
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
        assertTrue(testInstance.login("UserName", "password"));
    }

    @Test
    public void testLoginFailUsername(){
        assertFalse(testInstance.login("NotUserName", "password"));
    }

    @Test
    public void testLoginFailPassword(){
        assertFalse(testInstance.login("UserName", "Notpassword"));

    }

    @Test
    public void testLoginFailBoth(){
        assertTrue(testInstance.login("NotUserName", "Notpassword"));
    }

}