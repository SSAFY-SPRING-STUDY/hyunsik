package com.example.springpractice.domain.post.dto;

import com.example.springpractice.domain.member.controller.dto.MemberResponse;
import com.example.springpractice.domain.post.entity.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;
    // author를 String으로 응답하면?? -> 이름만 준다
    // MemberResponse로 감싸면?? -> id, username, nickname 을 함께 응답할 수 있다
    private final MemberResponse memberResponse;

    public static PostResponse from(PostEntity post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                MemberResponse.from(post.getAuthor())
        );
    }
}
