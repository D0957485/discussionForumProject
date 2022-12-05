package com.mycompany.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repo;

    public List<Comment> listAll() {
        return (List<Comment>) repo.findAll();
    }

    public void save(Comment comment) {
        repo.save(comment);
    }

    public Comment get(Integer id) throws CommentNotFoundException {
        Optional<Comment> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CommentNotFoundException("Could not find any users with ID" + id);
    }

    /*
    Implement for the delete handling
     */
    public void delete(Integer id) throws CommentNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new CommentNotFoundException("Could not find any users with ID" + id);
        }
        repo.deleteById(id);
    }
}
