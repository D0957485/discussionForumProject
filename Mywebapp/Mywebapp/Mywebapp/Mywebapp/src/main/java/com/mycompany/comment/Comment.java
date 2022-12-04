package com.mycompany.comment;

import com.mycompany.article.Article;
import org.springframework.lang.NonNull;
import javax.persistence.*;
@Entity
@Table(name = "comment_test")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, name = "replyName")
    private String replyName;

    @Column(length = 2000, nullable = false, name = "replyContent")
    private String replyContent;

    @Column(length = 45, nullable = false, name = "article_id")
    private String articleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +

                ", replyName='" + getReplyName() + '\'' +
                '}';
    }

}
