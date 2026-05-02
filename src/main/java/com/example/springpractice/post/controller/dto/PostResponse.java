package com.example.springpractice.post.controller.dto;

import com.example.springpractice.post.entity.PostEntity;

// 레코드는 최신문법
public record PostResponse(Long id, String title, String content, String author) {

    // PostEntity를 PostResponse로 변환하는 정적 팩토리 메서드이다
    public static PostResponse from(PostEntity postEntity) {
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getAuthor()
        );
    }
}
