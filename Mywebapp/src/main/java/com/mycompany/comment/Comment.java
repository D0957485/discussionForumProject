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
    private Integer comment_id;

    @Column(length = 2000, nullable = false, name = "comment_content")
    private String comment_content;

    @Column(nullable = false,name = "comment_time")
    private Time time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "user_id")
    private User user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "article_id")
    private Article article_id;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
}
