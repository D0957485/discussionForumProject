package com.mycompany.user;

import com.mycompany.article.Article;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = {"user_email"}))
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 20, nullable = false, name = "user_name")
  private String user_name;

  @Column(length = 40, nullable = false, name = "user_email",unique = true)
  private String user_email;

  @Column(length = 20, nullable = false, name = "user_password")
  private String user_password;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_email() {
    return user_email;
  }

  public void setUser_email(String user_email) {
    this.user_email = user_email;
  }

  public String getUser_password() {
    return user_password;
  }

  public void setUser_password(String user_password) {
    this.user_password = user_password;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + getId() +

            ", userName='" + getUser_name() + '\'' +
            '}';
  }
}
