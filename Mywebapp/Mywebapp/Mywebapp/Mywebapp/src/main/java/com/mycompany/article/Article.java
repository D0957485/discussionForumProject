package com.mycompany.article;

import javax.persistence.*;
@Entity
@Table(name = "articles_test")

public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 45, nullable = false, name = "createAccount")
  private String createAccount;

  @Column(length = 45, nullable = false, name = "title")
  private String title;

  @Column(length = 2000, nullable = false, name = "content")
  private String content;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
    return "User{" +
            "id=" + getId() +

            ", title='" + getTitle() + '\'' +
            '}';
  }
}
