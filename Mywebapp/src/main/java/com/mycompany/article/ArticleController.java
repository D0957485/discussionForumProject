package com.mycompany.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Service

public class ArticleController {
    @Autowired
    private ArticleService service;

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

    @GetMapping("/article/add")
    public String showNewFrom(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("pageTitle", "Add New User");
        return "article_add";
    }

    @PostMapping("/article/save")
    public String saveUser(Article article, RedirectAttributes ra) {
        service.save(article);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
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
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/articles";
    }
    @GetMapping("/article/{id}")
    public String showContent(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Article article = service.get(id);
            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "article_content";
        } catch (ArticleNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }
}