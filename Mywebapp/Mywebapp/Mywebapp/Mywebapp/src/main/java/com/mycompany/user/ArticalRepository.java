package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;


public interface ArticalRepository extends CrudRepository<Artical, Integer> {
  public Long countById(Integer id);
}
