package com.example.springpractice.post.entity;

import lombok.Getter;

// Lombok Getter 어노테이션으로 모든 필드의 getter가 자동 생성된다
@Getter
public class PostEntity {

    // static 변수로 모든 객체가 공유하며 생성될 때마다 1씩 증가한다
    private static long AUTO_INCREMENT_ID = 1L;

    private Long id;
    private String title;
    private String content;
    private String author;

    // 객체가 생성될 때 id를 자동 할당하고 전달받은 값을 필드에 저장한다
    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 제목과 내용만 수정 가능하며 작성자와 id는 변경하지 않는다
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
