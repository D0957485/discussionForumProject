package com.mycompany.article;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repo;

    public List<Article> listAll() {
        return (List<Article>) repo.findAll();
    }

    public void save(Article article) {
        repo.save(article);
    }

    public Article get(Integer id) throws ArticleNotFoundException {
        Optional<Article> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ArticleNotFoundException("Could not find any users with ID" + id);
    }

    /*
    Implement for the delete handling
     */
    public void delete(Integer id) throws ArticleNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ArticleNotFoundException("Could not find any users with ID" + id);
        }
        repo.deleteById(id);
    }
}
