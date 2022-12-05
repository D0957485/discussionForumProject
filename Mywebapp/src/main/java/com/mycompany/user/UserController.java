package com.mycompany.user;

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
public class UserController {
  @Autowired
  private UserService service;

  @GetMapping("/users")  /* the url you want to show */
  public String showUserList(Model model) {
    List<User> listUsers = service.listAll();
    model.addAttribute("listUsers", listUsers);
    return "users"; /* you write the html file */
  }

  @GetMapping("/users/new")
  public String showNewFrom(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("pageTitle", "Add New User");
    return "user_from";
  }

  @GetMapping("/users/login")
  public String showLoginFrom(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("pageTitle", "Login");
    return "user_login_from";
  }


  @PostMapping("/users/save")
  public String saveUser(User user, RedirectAttributes ra) {
    service.save(user);
    ra.addFlashAttribute("message", "The user has been saved successfully.");
    return "redirect:/";
  }


  /**
   * Immplementation of updated and delete
   */

  @GetMapping("/users/edit/{id}")
  public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
    try {
      User user = service.get(id);
      model.addAttribute("user", user);
      model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

      return "user_from";
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
      return "redirect:/users";
    }
  }

  @GetMapping("/users/delete/{id}")
  public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
    try {
      service.delete(id);
      ra.addFlashAttribute("message", "The user id" + id + "has been deleted");
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
    }
    return "redirect:/users";
  }
}
