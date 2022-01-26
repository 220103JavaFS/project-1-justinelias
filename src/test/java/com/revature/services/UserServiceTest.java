package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService testInstance;

    @Mock
    private UserDAO mockedDAO;

    private User testUser = new User();

    @BeforeEach
    public void setUp(){
        testUser = new User(
                12345,
                "UserName",
                "Passsssword123",
                "Tester",
                "McTest",
                "test@test.test",
                new UserRole(1, "Employee"));
        MockitoAnnotations.openMocks(this);
        testInstance = new UserService(mockedDAO);
        Mockito.when(mockedDAO.getUserById(12345)).thenReturn(testUser);
        Mockito.when(mockedDAO.getUserByUsername("UserName")).thenReturn(testUser);
        Mockito.when(mockedDAO.addUser(testUser)).thenReturn(true);
    }

    @Test
    public void testAddUser(){
        assertTrue(testInstance.addUser(testUser));
    }

    @Test
    public void testGetUserByIdSuccess(){
        assertEquals(testUser, testInstance.getUserById(testUser.getErsUsersId()));
    }

//    @Test
//    public void testGetUserByIdFail(){
//        assertNull(testInstance.getUserById(-5));
//    }

    @Test
    public void testGetUserByUsernameSuccess(){
        assertEquals(testUser, testInstance.getUserByUsername(testUser.getErsUsername()));
    }

//    @Test
//    public void testGetUserByUsernameFail(){
//        assertNull(testInstance.getUserByUsername(testUser.getErsUsername()));
//    }

}
