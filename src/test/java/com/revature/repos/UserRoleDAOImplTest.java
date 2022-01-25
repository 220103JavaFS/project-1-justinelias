package com.revature.repos;

import com.revature.models.UserRole;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRoleDAOImplTest {

    private static UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

    private static UserRole testRole = new UserRole(9, "CEO");

    @Test
    @Order(1)
    void testAddRole(){
        assertTrue(userRoleDAO.addRole(testRole));
    }

    @Test
    @Order(2)
    void testGetUserRole(){
        assertEquals(testRole, userRoleDAO.getRoleById(testRole.getErsUserRoleId()));
    }

}
