package com.revature.repos;

import com.revature.models.User;
import com.revature.models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserDAOImplTest {

    @Mock
    private UserDAO mockedDAO;

    private User testUser = new User(
            12345,
            "UserName",
            "Passsssword123",
            "Tester",
            "McTest",
            "test@test.test",
            new UserRole(1, "EMPLOYEE"));

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockedDAO = new UserDAOImpl();
        Mockito.when(mockedDAO.getUserById(12345)).thenReturn(testUser);
    }





}
