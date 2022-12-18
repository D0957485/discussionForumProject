package com.mycompany.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserLoginTest {

    @Autowired
    private UserController userController;

    @Test
    void loginTest() throws UserNotFoundException {
        Assertions.assertEquals("index",userController.logIn(1));
    }

    @Test
    void Invalidlogin() throws UserNotFoundException {
        Assertions.assertEquals(Exception.class,userController.logIn(600));
    }

}
