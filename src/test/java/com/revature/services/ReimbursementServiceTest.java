package com.revature.services;

import com.revature.models.*;
import com.revature.repos.ReimbursementDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReimbursementServiceTest {

    private ReimbursementService testInstance;

    @Mock
    private ReimbursementDAO mockedDAO;

    public Reimbursement testReimbursement = new Reimbursement();


    @BeforeEach
    public void setUp(){
        testReimbursement = new Reimbursement(1,
                65.35,
                Timestamp.valueOf("2022-01-01 14:05:00"),
                Timestamp.valueOf("2022-01-01 14:35:00"),
                "Business Dinner",
                new User(1,"username", "password", "Janet", "Fields", "email.email", new UserRole(1, "EMPLOYEE")),
                new User(1,"username", "password", "Janet", "Fields", "email.email", new UserRole(1, "EMPLOYEE")),
                new ReimbursementStatus(1, "RESOLVED"),
                new ReimbursementType(1,"FOOD"));
        MockitoAnnotations.openMocks(this);
         List<Reimbursement> list = new ArrayList<Reimbursement>();
         list.add(testReimbursement);
        testInstance = new ReimbursementService(mockedDAO);
        Mockito.when(mockedDAO.getAllReimbs()).thenReturn(list);
        Mockito.when(mockedDAO.addReimb(testReimbursement)).thenReturn(true);
        Mockito.when(mockedDAO.updateReimb(testReimbursement)).thenReturn(true);

    }
    @Test
    public void testAddReimb(){

    }

    @Test
    public void testUpdateReimb(){

    }

    public void testGetAllReimbs(){

    }
}
