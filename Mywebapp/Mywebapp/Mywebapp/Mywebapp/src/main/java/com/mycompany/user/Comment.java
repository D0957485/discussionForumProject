package com.mycompany.user;

import javax.persistence.*;

@Entity
@Table(name = "commentTable")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 45, nullable = false, name = "content")
  private String content;

  @Column(length = 45, nullable = false, name = "from")
  private String from;

  @Column(length = 45, nullable = false, name = "linkTopic")
  private String linkTopic;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getLinkTopic() {
    return linkTopic;
  }

  public void setLinkTopic(String linkTopic) {
    this.linkTopic = linkTopic;
  }

  @Override
  public String toString() {
    return "Artical{" +
            "id=" + getId() +

            ", topic='" + getContent() + '\'' +
            '}';
  }
}
