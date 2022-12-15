package com.mycompany.comment;

import com.mycompany.article.Article;
import com.mycompany.article.ArticleNotFoundException;
import com.mycompany.article.ArticleService;
import com.mycompany.user.User;
import com.mycompany.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Service

public class CommentController {
    @Autowired
    private CommentService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/comments")
    public String showCommentList(Model model) {
        List<Comment> listComments = service.listAll();
        model.addAttribute("listComments", listComments);
        return "comments";
    }
    @GetMapping("/comment/add/{articleId}/{userId}")
    public String showReplyCommentForm(@PathVariable("articleId") Integer articleId,@PathVariable("userId") Integer userId,Model model, RedirectAttributes ra) {
        Comment co = new Comment();
        List<Article> articles = articleService.listAll();
        List<User> users = userService.listAll();
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equals(articleId)) {
                co.setArticle_id(articles.get(i));
                break;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                co.setUser_id(users.get(i));
                break;
            }
        }
        model.addAttribute("comment",co);
        return "comment_add";
    }

    @PostMapping("/comment/save/{id}")
    public String saveComment(@PathVariable("id") Integer id,Comment comment, RedirectAttributes ra) {
        service.save(comment);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/article/" + id;
    }
    @GetMapping("/comment/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Comment comment = service.get(id);
            model.addAttribute("comment", comment);

            return "comment_add";
        } catch (CommentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/articlesIndex";
        }
    }

    @GetMapping("/user/personal/delete/{id}/{userId}")
    public String deleteComment(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId, RedirectAttributes ra) {
        try {
            System.out.println(service.get(id).getId());
            System.out.println(service.get(id).getComment_content());
            service.delete(id);
            ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
            return "redirect:/user/personal/comment/" + userId;
        } catch (CommentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/comments";
    }


    @GetMapping("commentReply")
    public String showReplyArticleList(Model model) {
        List<Comment> listComments = service.listAll();
        model.addAttribute("listComments", listComments);
        return "comment_index";
    }
    @GetMapping("/article/comment/{id}")
    public String showContent(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        List<Comment> listComments = service.listAll();

        model.addAttribute("listComments", listComments);
        model.addAttribute("getId", id);
        return "comment_index";
    }

    @GetMapping("/user/personal/comment/{id}")
    public String showPersonalArticle(@PathVariable("id") Integer id, Model model, RedirectAttributes ra, HttpSession session) {

        List<Comment> listComments = service.listAll();
        model.addAttribute("userId",id);
        model.addAttribute("listComments",listComments);
        return "personal_comment";
    }
    @GetMapping("/user/personal/comment/edit/{id}")
    public String showPersonalEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Comment comment = service.get(id);
            model.addAttribute("comment", comment);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "personal_comment_edit";
        } catch (CommentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }
    @PostMapping("/user/personal/comment/save")
    public String savePersonalArticle(Comment comment, RedirectAttributes ra) {
        service.save(comment);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/user/personal/comment/" + comment.getUser_id().getId();
    }
}
