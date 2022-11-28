package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Integer> {
  public Long countById(Integer id);
}
