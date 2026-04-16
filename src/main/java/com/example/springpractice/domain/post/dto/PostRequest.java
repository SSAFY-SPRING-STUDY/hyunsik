package com.example.springpractice.domain.post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {
    private final String title;
    private final String content;
    private final String author;

}
