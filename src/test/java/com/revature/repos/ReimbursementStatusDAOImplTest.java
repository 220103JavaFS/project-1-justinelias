package com.revature.repos;

import com.revature.models.ReimbursementStatus;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ReimbursementStatusDAOImplTest {
    private static ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();

    private static ReimbursementStatus testStatus = new ReimbursementStatus(8, "Approved");

    @Test
    @Order(1)
    void testAddStatus(){
        assertTrue(reimbursementStatusDAO.addStatus(testStatus));

    }

    @Test
    @Order(2)
    void testGetStatusById(){
        assertEquals(testStatus, reimbursementStatusDAO.getStatusById(testStatus.getReimbStatusId()));
    }

    @Test
    @Order(3)
    void testGetStatusByIdFail(){
        assertNull(reimbursementStatusDAO.getStatusById(-2));
    }
}
