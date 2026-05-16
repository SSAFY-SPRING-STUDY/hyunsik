package com.example.springpractice.domain.member.controller.dto;

import com.example.springpractice.domain.member.entity.MemberEntity;

// 레코드는 필드 선언이랑 생성자, getter 를 한 줄에 다 써주는 최신 문법
// request.username() 이렇게 getter 없이 바로 접근 가능함
public record MemberRequest(String username, String password, String nickname) {

    // 인스턴스 메서드로 바꿔서 request.toEntity() 형태로 쓸 수 있게 함
    // MemberEntity.create() 를 호출해서 id 자동 부여까지 한 번에 처리
    public MemberEntity toEntity() {
        return MemberEntity.create(username, password, nickname);
    }
}
