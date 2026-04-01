package com.example.springpractice;

public class CreatePostRequest {
    public String title;
    public String content;
    public String author;

    // 이 클래스가 있어야 요청을 자동으로 파싱해줌
    @Override
    public String toString() {
        return "CreatePostRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
