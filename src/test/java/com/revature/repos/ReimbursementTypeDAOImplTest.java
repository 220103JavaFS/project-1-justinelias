package com.revature.repos;

import com.revature.models.ReimbursementType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReimbursementTypeDAOImplTest {

    private static ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();

    private static ReimbursementType testType = new ReimbursementType(9, "Transportation");

    @Test
    @Order(1)
    void testAddRole(){
        assertTrue(reimbursementTypeDAO.addType(testType));
    }

    @Test
    @Order(2)
    void testGetUserRole(){
        assertEquals(testType, reimbursementTypeDAO.getTypeById(testType.getReimbTypeId()));
    }
}
