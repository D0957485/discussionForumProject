package com.mycompany.comment;

import com.mycompany.article.Article;
import com.mycompany.user.User;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2000, nullable = false, name = "comment_content")
    private String comment_content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "article_id")
    private Article article_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Article getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Article article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", replyId='" + getUser_id() + '\'' +
                '}';
    }
}

