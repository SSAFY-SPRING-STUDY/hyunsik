package com.example.springpractice.controller.dto;


import lombok.Getter;

@Getter
public class PostResponse {
    private final Long id; //Wrapper 클래스는 없어도 null 반환, 방어적
    private final String title;
    private final String author;
    private final String content;

    public PostResponse(Long id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
