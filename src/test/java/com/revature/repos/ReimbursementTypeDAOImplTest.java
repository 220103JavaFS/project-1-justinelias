package com.revature.repos;

import com.revature.models.ReimbursementType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReimbursementTypeDAOImplTest {

    private static ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();

    private static ReimbursementType testType = new ReimbursementType(6, "Lodging");

    @Test
    @Order(1)
    void testAddRole(){
        assertTrue(reimbursementTypeDAO.addType(testType));
    }

    @Test
    @Order(2)
    void testGetUserRoleSuccess(){
        assertEquals(testType, reimbursementTypeDAO.getTypeById(testType.getReimbTypeId()));
    }

    @Test
    @Order(3)
    void testGetUserRoleFail(){
        assertNull(reimbursementTypeDAO.getTypeById(-5));
    }
}
