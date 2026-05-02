package com.example.springpractice.post.controller.dto;

// 클라이언트로부터 게시글 생성 및 수정 요청 데이터를 받는 DTO 클래스이다
public class PostRequest {

    private String title;
    private String content;
    private String author;

    public PostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
