package com.mycompany.article;

import com.mycompany.user.User;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "article",uniqueConstraints = @UniqueConstraint(columnNames = {"article_title"}))

public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer article_id;

  @Column(length = 45, nullable = false, name = "article_title")
  private String article_title;

  @Column(length = 45, nullable = false, name = "article_content")
  private String article_content;

  @Column(nullable = false,name = "article_time")
  private Time article_time;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(nullable = false,name = "user_id")
  private User user_id;

  public Integer getArticle_id() {
    return article_id;
  }

  public void setArticle_id(Integer article_id) {
    this.article_id = article_id;
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

  public Time getArticle_time() {
    return article_time;
  }

  public void setArticle_time(Time article_time) {
    this.article_time = article_time;
  }


  public User getUser_id() {
    return user_id;
  }

  public void setUser_id(User user_id) {
    this.user_id = user_id;
  }
}
