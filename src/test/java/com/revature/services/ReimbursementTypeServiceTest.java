package com.revature.services;

import com.revature.models.ReimbursementType;
import com.revature.repos.ReimbursementTypeDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ReimbursementTypeServiceTest {

    private ReimbursementTypeService testInstance;

    @Mock
    private ReimbursementTypeDAO mockedDAO;

    private ReimbursementType testType = new ReimbursementType();

    @BeforeEach
    public void setUp(){
        testType = new ReimbursementType(1, "Lodging");
        MockitoAnnotations.openMocks(this);
        testInstance = new ReimbursementTypeService(mockedDAO);
        Mockito.when(mockedDAO.getTypeById(1)).thenReturn(testType);
        Mockito.when(mockedDAO.addType(testType)).thenReturn(true);
    }

    @Test
    public void testAddType(){
        assertTrue(testInstance.addType(testType));
    }

    @Test
    public void testGetTypeByIdSuccess(){
        assertEquals(testType, testInstance.getTypeById(testType.getReimbTypeId()));
    }

    @Test
    public void testGetTypeByIdFail(){
        assertNull(testInstance.getTypeById(-5));
    }

}
