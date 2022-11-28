package com.mycompany.user;

import javax.persistence.*;

@Entity
@Table(name = "articalTable")
public class Artical {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 45, nullable = false, name = "topic")
  private String topic;

  @Column(length = 45, nullable = false, name = "content")
  private String content;

  @Column(length = 45, nullable = false, name = "from")
  private String from;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
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


  @Override
  public String toString() {
    return "Artical{" +
            "id=" + getId() +

            ", topic='" + getTopic() + '\'' +
            '}';
  }
}
