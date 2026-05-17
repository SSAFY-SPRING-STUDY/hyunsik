package com.example.springpractice.domain.post.dto;

import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.post.entity.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {
    private final String title;
    private final String content;

    // author를 요청 바디에서 받으면?? -> 클라이언트가 남의 이름을 마음대로 넣을 수 있다
    // 그래서 작성자 정보는 세션에서 꺼내서 서버가 직접 넣어준다
    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title, content, author);
    }
}
