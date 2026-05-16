package com.example.springpractice.domain.member.controller.dto;

import com.example.springpractice.domain.member.entity.MemberEntity;

public record MemberResponse(Long id, String username, String nickname) {

    // from() 이라는 이름으로 통일 - entity 받아서 응답 DTO 로 변환해주는 정적 팩토리
    public static MemberResponse from(MemberEntity member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getNickname());
    }
}
