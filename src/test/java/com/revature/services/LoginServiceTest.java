package com.revature.services;

import com.revature.models.UserDTO;
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

    private UserDTO testUser = new UserDTO();

//    @BeforeEach
//    public void setUp(){
//        testUser.username = "agent";
//        testUser.password = "password";
//        MockitoAnnotations.openMocks(this);
//        testInstance = new LoginService(mockedDAO);
//        Mockito.when(mockedDAO.login("agent")).thenReturn(testUser);
//    }

    @Test
    public void testLoginSuccess(){

    }

    @Test
    public void testLoginFailUsername(){

    }

    @Test
    public void testLoginFailPassword(){

    }

    @Test
    public void testLoginFailBoth(){

    }

}