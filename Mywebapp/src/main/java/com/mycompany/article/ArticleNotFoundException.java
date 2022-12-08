package com.mycompany.article;

public class ArticleNotFoundException extends Throwable {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
