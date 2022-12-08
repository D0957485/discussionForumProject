package com.mycompany.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Service

public class CommentController {
    @Autowired
    private CommentService service;

    @GetMapping("/comments")
    public String showCommentList(Model model) {
        List<Comment> listComments = service.listAll();
        model.addAttribute("listComments", listComments);
        return "comments";
    }


    @GetMapping("/comments/new")
    public String showNewFrom(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("pageTitle", "Add New User");
        return "comment_from";
    }

    @PostMapping("/comments/save")
    public String saveComment(Comment comment, RedirectAttributes ra) {
        service.save(comment);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/comments";
    }
    @GetMapping("/comments/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Comment comment = service.get(id);
            model.addAttribute("comment", comment);

            return "comment_from";
        } catch (CommentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/comments";
        }
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
        } catch (CommentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/comments";
    }
    @GetMapping("articles/commentReply")
    public String showReplyArticleList(Model model) {
        List<Comment> listComments = service.listAll();
        model.addAttribute("listComments", listComments);
        return "comment_reply";
    }
    @GetMapping("/articles/show1/{articleId}")
    public String showContent(@PathVariable("articleId") Integer id, Model model, RedirectAttributes ra) {
        try {
            List<Comment> listComments = service.listAll();
            Comment comment = service.get(id);
            model.addAttribute("listComments", listComments);
            model.addAttribute("getId", comment);
            return "comment_reply";
        } catch (CommentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/articles/show/{articleId}";
        }
    }
}
