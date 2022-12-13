package com.mycompany.article;

import com.mycompany.user.User;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "article",uniqueConstraints = @UniqueConstraint(columnNames = {"article_title"}))

public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 45, nullable = false, name = "article_title")
  private String article_title;

  @Column(length = 2000, nullable = false, name = "article_content")
  private String article_content;


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(nullable = false,name = "user_id")
  private User user_id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getArticle_title() {
    return article_title;
  }

  public void setArticle_title(String article_title) {
    this.article_title = article_title;
  }

  public String getArticle_content() {
    return article_content;
  }

  public void setArticle_content(String article_content) {
    this.article_content = article_content;
  }


  public User getUser_id() {
    return user_id;
  }

  public void setUser_id(User user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + getId() +

            ", title='" + getArticle_title() + '\'' +
            '}';
  }

}
