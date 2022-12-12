package com.mycompany.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImple{

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        var user_info = userRepository.findAll();
        var users = new ArrayList<User>();
        user_info.forEach(e ->users.add(e));
        return users;
    }



    public void UserAdd(User user){
        userRepository.save(user);
    }


  /*@Autowired
  private UserRepository repo;*/

  /*public List<User> listAll() {
    return (List<User>) repo.findAll();
  }

  public void save(User user) {
    repo.save(user);
  }*/

  /*public User get(Integer user_id) throws UserNotFoundException {
    Optional<User> result = repo.findById(user_id);
    if (result.isPresent()) {
      return result.get();
    }
    throw new UserNotFoundException("Could not find any users with ID" + user_id);
  }*/

  /*public void delete(Integer user_id) throws UserNotFoundException {
    Long count = repo.countById(user_id);
    if (count == null || count == 0) {
      throw new UserNotFoundException("Could not find any users with ID" + user_id);
    }
    repo.deleteById(user_id);
  }*/
}
