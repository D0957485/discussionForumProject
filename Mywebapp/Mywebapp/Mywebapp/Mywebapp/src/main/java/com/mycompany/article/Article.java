package com.mycompany.article;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.user.User;

import javax.persistence.*;

@Entity
@Table(name = "articles_test")

public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer article_id;

  @Column(length = 45, nullable = false, name = "createAccount")
  private String createAccount;

  @Column(length = 45, nullable = false, name = "title")
  private String title;

  @Column(length = 2000, nullable = false, name = "content")
  private String content;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="user_id", referencedColumnName="id")
  private User user;

  public Integer getArticle_id() {
    return article_id;
  }

  public void setArticle_id(Integer article_id) {
    this.article_id = article_id;
  }

  public String getCreateAccount() {
    return createAccount;
  }

  public void setCreateAccount(String createAccount) {
    this.createAccount = createAccount;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Article{" +
            "article_id=" + article_id +
            ", createAccount='" + createAccount + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
  }
}
