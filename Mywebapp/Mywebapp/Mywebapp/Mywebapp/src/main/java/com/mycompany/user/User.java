package com.mycompany.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.article.Article;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Entity
@Table(name = "users_test")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer user_id;

  @Column(length = 45, nullable = false, name = "nickName")
  private String nickName;

  @Column(length = 45, nullable = false, name = "account")
  private String account;

  @Column(length = 45, nullable = false, name = "password")
  private String password;


  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  private Set<Article> articles;

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public String toString() {
    return "User{" +
            "user_id=" + user_id +
            ", nickName='" + nickName + '\'' +
            ", account='" + account + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
