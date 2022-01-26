package com.revature.services;

import com.revature.models.UserRole;
import com.revature.repos.UserRoleDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleServiceTest {

    private UserRoleService testInstance;

    @Mock
    private UserRoleDAO mockedDAO;

    private UserRole testRole = new UserRole();

    @BeforeEach
    public void setUp(){
        testRole = new UserRole(9, "CEO");
        MockitoAnnotations.openMocks(this);
        testInstance = new UserRoleService(mockedDAO);
        Mockito.when(mockedDAO.getRoleById(9)).thenReturn(testRole);
        Mockito.when(mockedDAO.addRole(testRole)).thenReturn(true);
    }

    @Test
    public void testAddRole(){
        assertTrue(testInstance.addRole(testRole));
    }

    @Test
    public void testGetRoleByIdSuccess(){
        assertEquals(testRole, testInstance.getRoleById(testRole.getErsUserRoleId()));
    }

    @Test
    public void testGetRoleByIdFail(){
        assertNull(testInstance.getRoleById(-5));
    }

}
