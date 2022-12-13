package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  public Long countById(Integer id);
  public Long countByAccount(String account);
  public User findByAccount(String account);
}
