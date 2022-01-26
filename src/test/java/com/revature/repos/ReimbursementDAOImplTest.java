package com.revature.repos;

import com.revature.models.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Timestamp;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ReimbursementDAOImplTest {

    private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private static Reimbursement testReimbursement = new Reimbursement(
            1,
            65.35,
            Timestamp.valueOf("2022-01-01 14:05:00"),
            Timestamp.valueOf("2022-01-01 14:35:00"),
            "Business Dinner",
            new User(1,"username", "password", "Janet", "Fields", "email.email", new UserRole(1, "EMPLOYEE")),
            new User(1,"username", "password", "Janet", "Fields", "email.email", new UserRole(1, "EMPLOYEE")),
            new ReimbursementStatus(1, "RESOLVED"),
            new ReimbursementType(1,"FOOD")
            );
    @Test
    @Order(1)
    void testAddReimb(){
        //test code
    }

    @Test
    @Order(3)
    void testUpdateReimbursement(){

    }

}
