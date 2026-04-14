package com.example.springpractice.domain.member.controller.dto;

import com.example.springpractice.domain.member.entity.MemberEntity;

public record MemberRequest(
        String loginId,
        String password,
        String name

        // MemberRequest request 가 있다고 치면
        // request.getLoginId() x
        // request.loginId() o

) {
    public static MemberEntity toEntity(MemberRequest request) {
        return new MemberEntity(
                request.loginId(),
                request.password(),
                request.name()


        );
    }
}
