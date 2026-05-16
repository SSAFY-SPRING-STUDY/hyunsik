package com.example.springpractice.domain.post.entity;

import com.example.springpractice.domain.member.entity.MemberEntity;
import lombok.Getter;

@Getter
public class PostEntity {

    private static long AUTO_INCREMENT = 1L;

    private final Long id;
    private String title;
    private String content;
    // author를 String으로 하면?? -> 이름 하나만 알 수 있다
    // MemberEntity를 직접 참조하면?? -> post.getAuthor().getNickname() 처럼 회원 정보 전부 접근 가능하다
    private final MemberEntity author;

    private PostEntity(Long id, String title, String content, MemberEntity author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(AUTO_INCREMENT++, title, content, author);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
