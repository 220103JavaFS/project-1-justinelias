package com.revature.repos;

import com.revature.models.ReimbursementStatus;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class ReimbursementStatusDAOImplTest {
    private static ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();

    private static ReimbursementStatus testStatus = new ReimbursementStatus(1, "Approved");

    @Test
    @Order(1)
    void testAddStatus(){

    }

    @Test
    @Order(2)
    void testGetStatusById(){

    }
}
