package com.revature.repos;

import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

public class UserDAOImplTest {

    @Mock
    private UserDAO mockedDao;

    private User testUser = new User();

    @BeforeEach
    public void setUp(){
        testUser = new User(
                12345,
                "UserName",
                "Passsssword123",
                "Tester",
                "McTest",
                "test@test.test"
        );
    }





}
