package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();

        user.setFirstName("abcjsdksjdk");

       User savedUser = repo.save(user);
       System.out.println(user);
       Assertions.assertThat(savedUser).isNotNull();
       Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll() {
       Iterable<User> users = repo.findAll();
       Assertions.assertThat(users).hasSizeGreaterThan(0);

       for(User user: users) {
           System.out.println(user);
       }
    }

    @Test
    public void testUpdate() {
        Integer userId = 3;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setFirstName("hghhhhheeee");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getFirstName()).isEqualTo("hghhhhheeee");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 6;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
