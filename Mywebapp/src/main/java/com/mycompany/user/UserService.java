package com.mycompany.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository repo;

  public List<User> listAll() {
    return (List<User>) repo.findAll();
  }

  public User loginUser(User checkUser) throws UserNotFoundException {

    int count = repo.countByAccount(checkUser.getAccount());
    if (count == 0) {
      throw new UserNotFoundException("Could not find any users with Account " + checkUser.getAccount());
    }
    User hold = repo.findByAccount(checkUser.getAccount());
    if(Objects.equals(checkUser.getPassword(), hold.getPassword())) {

      return hold;

    } else {
      throw new UserNotFoundException("Password is not correct");
    }
  }

  public void save(User user) {
    repo.save(user);
  }

  public User get(Integer id) throws UserNotFoundException {
    Optional<User> result = repo.findById(id);
    if (result.isPresent()) {
      return result.get();
    }
    throw new UserNotFoundException("Could not find any users with ID" + id);
  }

  public void delete(Integer id) throws UserNotFoundException {
    Long count = repo.countById(id);
    if (count == null || count == 0) {
      throw new UserNotFoundException("Could not find any users with ID" + id);
    }
    repo.deleteById(id);
  }
}
