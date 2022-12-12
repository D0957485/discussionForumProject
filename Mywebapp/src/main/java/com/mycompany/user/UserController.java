package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Service
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserServiceImple userServiceImple;

    @GetMapping("/users/login")
    public String Login(){
        return "user_login_form";
    }

    @RequestMapping("/users/check")
    public String UserCheck(String user_email, String user_password,HttpSession session){
       try {
           System.out.println(user_email + user_password);
           List<User> user_info = userServiceImple.findAll();
           for(int i = 0; i < user_info.size(); i++){
               if(user_info.get(i).getUser_email().equals(user_email) && user_info.get(i).getUser_password().equals(user_password)){
                   System.out.println("Login Successfully user : " + user_info.get(i).getUser_email());
                   session.setAttribute("Usersession",user_info.get(i));
                   return "redirect:/users";
               }
           }
       }catch (Exception e){
           System.out.println("User Not Found");
       }
        return "redirect:/users/login";
    }

    @GetMapping ("/users")
    public String loginSuccess(){
        return "home";
    }

    @RequestMapping("/users/register")
    public String registration(){
        return "registration";
    }

     @PostMapping("/users/register")
    public String saveUser(User user, RedirectAttributes ra) {
      try {
          userServiceImple.UserAdd(user);
          ra.addFlashAttribute("message", "The user has been saved successfully.");
      }catch (Exception e){
          System.out.println("Email has been registration");
          return "redirect:/users/register";
      }
      return "redirect:/";
    }




  /*@GetMapping("/users")
  public String showUserList(Model model) {
    List<User> listUsers = service.listAll();
    model.addAttribute("listUsers", listUsers);
    return "users";
  }*/

  /*@GetMapping("/users/new")
  public String showNewFrom(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("pageTitle", "Add New User");
    return "user_from";
  }*/

  /*@GetMapping("/users/login")
  public String showLoginFrom(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("pageTitle", "Login");
    return "user_login_from";
  }*/


  /*@PostMapping("/users/save")
  public String saveUser(User user, RedirectAttributes ra) {
    service.save(user);
    ra.addFlashAttribute("message", "The user has been saved successfully.");
    return "redirect:/";
  }*/


  /**
   * Immplementation of updated and delete
   */

  /*@GetMapping("/users/edit/{user_id}")
  public String showEditForm(@PathVariable("user_id") Integer user_id, Model model, RedirectAttributes ra) {
    try {
      User user = service.get(user_id);
      model.addAttribute("user", user);
      model.addAttribute("pageTitle", "Edit User (ID: " + user_id + ")");

      return "user_from";
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
      return "redirect:/users";
    }
  }

  @GetMapping("/users/delete/{user_id}")
  public String deleteUser(@PathVariable("user_id") Integer user_id, RedirectAttributes ra) {
    try {
      service.delete(user_id);
      ra.addFlashAttribute("message", "The user id" + user_id + "has been deleted");
    } catch (UserNotFoundException e) {
      ra.addFlashAttribute("message", e.getMessage());
    }
    return "redirect:/users";
  }*/
}
