package com.mycompany.article;

import com.mycompany.comment.Comment;
import com.mycompany.comment.CommentNotFoundException;
import com.mycompany.comment.CommentService;
import com.mycompany.user.User;
import com.mycompany.user.UserNotFoundException;
import com.mycompany.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Service

public class ArticleController {
    @Autowired
    private ArticleService service;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @GetMapping("/articles")
    public String showArticleList(Model model) {
        List<Article> listArticles = service.listAll();
        model.addAttribute("listArticles", listArticles);
        return "articles";
    }

    @GetMapping("articlesIndex")
    public String showWriteArticleList(Model model) {
        List<Article> listArticles = service.listAll();
        model.addAttribute("listArticles", listArticles);
        return "articles_index";
    }

    @GetMapping("/article/add/{id}")
    public String showNewFrom(@PathVariable("id") Integer id,Model model, RedirectAttributes ra) {

        Article ar = new Article();
        List<User> users = userService.listAll();
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                ar.setUser_id(users.get(i));
                model.addAttribute("article", ar);
                model.addAttribute("pageTitle", "Add New User");
                return "article_add";
            }
        }
        return "articles_index";
    }

    @PostMapping("/article/save/{id}")
    public String saveUser(@PathVariable("id") Integer id,Article article, RedirectAttributes ra) {
        try {
            service.save(article);
            ra.addFlashAttribute("message", "The user has been saved successfully.");
        }catch (Exception e) {
            System.out.println("Title has been appear");
            return "redirect:/article/add/" + id;
        }
        return "redirect:/articlesIndex";
    }

    @GetMapping("/article/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Article article = service.get(id);
            model.addAttribute("article", article);

            return "article_add";
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/articles";
        }
    }

    @GetMapping("/article/delete/{id}")
    public String rootDeletePersonalComment(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            try {
                List<Comment> commentList = commentService.listAll();
                for (int i = 0; i < commentList.size(); i++) {
                    if(commentList.get(i).getArticle_id().getId().equals(id)) {
                        commentList.get(i).setArticle_id(null);
                        commentList.get(i).setUser_id(null);
                        commentService.save(commentList.get(i));
                        commentService.delete(commentList.get(i).getId());
                    }
                }
            }catch (CommentNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
            }
            List<Article> articleList = service.listAll();
            for (int i=0; i < articleList.size(); i++) {
                if (articleList.get(i).getId().equals(id)) {
                    articleList.get(i).setUser_id(null);
                    service.save(articleList.get(i));
                    break;
                }
            }
            service.delete(id);
        }catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/articles";
    }
    @GetMapping("/article/{id}")
    public String showContent(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Article article = service.get(id);
            List<Comment> listComments = commentService.listAll();
            model.addAttribute("article", article);
            model.addAttribute("listComments",listComments);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "article_content";
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }
    @GetMapping("/user/personal/article/{id}") // id is user id
    public String showPersonalArticle(@PathVariable("id") Integer id, Model model, RedirectAttributes ra, HttpSession session) {

        List<Article> listArticles = service.listAll();
        model.addAttribute("userId",id);
        model.addAttribute("listArticles",listArticles);
        return "personal_article";
    }

    @GetMapping("/user/personal/article/edit/{id}")
    public String showPersonalEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Article article = service.get(id);
            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "personal_article_edit";
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/user/personal/article/delete/{id}/{userId}")
    public String deletePersonalComment(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId, RedirectAttributes ra) {
        try {
            try {
                List<Comment> commentList = commentService.listAll();
                for (int i = 0; i < commentList.size(); i++) {
                    if(commentList.get(i).getArticle_id().getId().equals(id)) {
                        commentList.get(i).setArticle_id(null);
                        commentList.get(i).setUser_id(null);
                        commentService.save(commentList.get(i));
                        commentService.delete(commentList.get(i).getId());
                    }
                }
            }catch (CommentNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
            }
            List<Article> articleList = service.listAll();
            for (int i=0; i < articleList.size(); i++) {
                if (articleList.get(i).getId().equals(id)) {
                    articleList.get(i).setUser_id(null);
                    service.save(articleList.get(i));
                    break;
                }
            }
            service.delete(id);
        }catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/user/personal/article/" + userId;
    }

    @PostMapping("/user/personal/article/save")
    public String savePersonalArticle(Article article, RedirectAttributes ra) {
        service.save(article);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/user/personal/article/" + article.getUser_id().getId();
    }
}