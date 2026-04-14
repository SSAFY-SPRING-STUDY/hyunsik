package com.example.springpractice.domain.post.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostResponse {
    private final Long id; //Wrapper 클래스는 없어도 null 반환, 방어적
    private final String title;
    private final String author;
    private final String content;

}
