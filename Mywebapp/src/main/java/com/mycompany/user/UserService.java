package com.mycompany.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository repo;

  public List<User> listAll() {
    return (List<User>) repo.findAll();
  }
  public List<User> findAll(){
    var user_info = repo.findAll();
    var users = new ArrayList<User>();
    user_info.forEach(e ->users.add(e));
    return users;
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