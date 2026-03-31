package com.example.springpractice.entity;

import lombok.Getter;

// 데이터베이스에 직접적으로 들어갈 객체 생성
@Getter
public class PostEntity {

    private static long AUTO_INCREMENT = 1L;

    private long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
