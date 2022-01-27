package com.revature.services;

import com.revature.models.ReimbursementStatus;

import com.revature.repos.ReimbursementStatusDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(testInstance.addStatus(testStatus));
    }

    @Test
    public void testGetStatusByIdSuccess(){
        assertEquals(testStatus, testInstance.getStatusById(testStatus.getReimbStatusId()));
    }

    @Test
    public void testGetStatusByIdFail(){
        assertNull(testInstance.getStatusById(-1));
    }
}
