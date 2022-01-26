package com.revature.services;

import com.revature.models.ReimbursementStatus;

import com.revature.repos.ReimbursementStatusDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReimbursementStatusServiceTest {

    private ReimbursementStatusService testInstance;

    @Mock
    private ReimbursementStatusDAO mockedDAO;

    private ReimbursementStatus testStatus = new ReimbursementStatus();

    @BeforeEach
    public void setUp(){
        testStatus = new ReimbursementStatus(1, "Approved");
        MockitoAnnotations.openMocks(this);
        testInstance = new ReimbursementStatusService(mockedDAO);
        Mockito.when(mockedDAO.getStatusById(1)).thenReturn(testStatus);
        Mockito.when(mockedDAO.addStatus(testStatus)).thenReturn(true);
    }

    @Test
    public void testAddStatus(){

    }

    @Test
    public void testGetStatusByIdSuccess(){

    }
}
