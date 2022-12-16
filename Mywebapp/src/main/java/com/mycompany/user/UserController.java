package com.mycompany.user;

import com.mycompany.article.Article;
import com.mycompany.article.ArticleNotFoundException;
import com.mycompany.article.ArticleService;
import com.mycompany.comment.Comment;
import com.mycompany.comment.CommentNotFoundException;
import com.mycompany.comment.CommentService;
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
public class UserController {
  @Autowired
  private UserService service;

  @Autowired
  private ArticleService articleService;

  @Autowired
  private CommentService commentService;

  @GetMapping("/users")  /* the url you want to show */
  public String showUserList(Model model) {
    List<User> listUsers = service.listAll();
    model.addAttribute("listUsers", listUsers);
    return "users"; /* you write the html file */
  }
  // not log in
  @GetMapping("/")
  public String notIn(HttpSession session) {
      User user = new User();
      session.setAttribute("Usersession",user);
      return "index";
  }
  // log in
  @GetMapping("/{id}")
  public String logIn(@PathVariable("id") Integer id,HttpSession session) {

    return "index";
  }
  // log out
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    return "redirect:/";
  }
  @RequestMapping("/user/register")
  public String showRegisterForm(){
    return "user_register";
  }

  @PostMapping("/user/register")
  public String registerUser(User user, RedirectAttributes ra) {
    try {
      service.save(user);
      ra.addFlashAttribute("message", "The user has been saved successfully.");
    }catch (Exception e){
      System.out.println("Email has been registration");
      return "redirect:/user/register";
    }
    return "redirect:/";
  }

  @GetMapping("/user/login")
  public String showLoginForm() {
    return "user_login";
  }

  @RequestMapping("/user/check")
  public String UserCheck(String user_email, String user_password, HttpSession session){
    try {

      System.out.println(user_email + user_password);
      List<User> user_info = service.findAll();
      for(int i = 0; i < user_info.size(); i++){
        if(user_info.get(i).getUser_email().equals(user_email) && user_info.get(i).getUser_password().equals(user_password)){
          System.out.println("Login Successfully user : " + user_info.get(i).getUser_email());
          session.setAttribute("Usersession",user_info.get(i));
          return "redirect:/" + user_info.get(i).getId();
        }
      }
    }catch (Exception e){
      System.out.println("User Not Found");
    }
    return "redirect:/user/login";
  }

  @PostMapping("/user/save")
  public String saveUser(User user, RedirectAttributes ra) {
    service.save(user);
    ra.addFlashAttribute("message", "The user has been saved successfully.");
    return "redirect:/users";
  }

  /**
   * Immplementation of updated and delete
   */

  @GetMapping("/user/edit/{id}")
  public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
    try {
      User user = service.get(id);
      model.addAttribute("user", user);
      model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

      return "user_edit";
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
      return "redirect:/users";
    }
  }

//  @GetMapping("/user/delete/{id}")
//  public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
//    try {
//      service.delete(id);
//      ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
//    } catch (UserNotFoundException e) {
//      ra.addFlashAttribute("message", e.getMessage());
//    }
//    return "redirect:/users";
//  }

  @GetMapping("/user/personal/{id}")
  public String showEditPersonal(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
    try {
      User user = service.get(id);
      model.addAttribute("user", user);
      model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

      return "personal_index";
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
      return "redirect:/";
    }
  }

  @GetMapping("/user/personal/edit/{id}")
  public String showPersonalEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
    try {
      User user = service.get(id);
      model.addAttribute("user", user);
      model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

      return "personal_edit";
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
      return "redirect:/";
    }
  }

  @PostMapping("/user/personal/save")
  public String savePersonalUser(User user, RedirectAttributes ra,HttpSession session) {
    service.save(user);
    session.setAttribute("Usersession",user);
    ra.addFlashAttribute("message", "The user has been saved successfully.");
    return "redirect:/user/personal/" + user.getId();
  }
  @GetMapping("/user/delete/{id}")
  public String rootDeletePersonalComment(@PathVariable("id") Integer id, RedirectAttributes ra) {

    try {
      try {
        //刪除User所有發的文
        List<Article> articleList = articleService.listAll();
        for (int i=0; i < articleList.size(); i++) {
          if (articleList.get(i).getUser_id().getId().equals(id)) {
            try {
              // 刪除Article 裡面的所有回覆
              List<Comment> commentList = commentService.listAll();
              for (int j = 0; j < commentList.size(); j++) {
                if (commentList.get(j).getArticle_id().getId().equals(i)) {
                  commentList.get(j).setArticle_id(null);
                  commentList.get(j).setUser_id(null);
                  commentService.save(commentList.get(j));
                  commentService.delete(commentList.get(j).getId());
                }
              }
            }catch (CommentNotFoundException e) {
              ra.addFlashAttribute("message", e.getMessage());
            }
            articleList.get(i).setUser_id(null);
            articleService.save(articleList.get(i));
            articleService.delete(articleList.get(i).getId());
          }
        }
      }catch (ArticleNotFoundException e) {
        ra.addFlashAttribute("message", e.getMessage());
      }
      service.delete(id);
    }catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
    }
    return "redirect:/users";
  }
}
