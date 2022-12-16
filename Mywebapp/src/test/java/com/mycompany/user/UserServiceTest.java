package com.mycompany.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

  @Test
  void listAll() {
    UserService sc = new UserService();
    sc.listAll();
  }
}