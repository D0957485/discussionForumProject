package com.mycompany.user;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest(UserController.class)

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class UserControllerTest {

  HttpSession session;

  @Test
  void userCheck() {

    UserController sc = new UserController();


    String reslut = sc.UserCheck("aaa@gmail.com", "000", session);
    Assertions.assertEquals("redirect:/user/login",reslut);

  }



}