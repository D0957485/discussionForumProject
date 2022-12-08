package com.mycompany.article;

import org.springframework.data.repository.CrudRepository;


public interface ArticleRepository extends CrudRepository<Article, Integer> {
  public Long countById(Integer id);
}
