package com.mycompany.comment;

public class CommentNotFoundException extends Throwable {
    public CommentNotFoundException(String message) {
        super(message);
    }
}