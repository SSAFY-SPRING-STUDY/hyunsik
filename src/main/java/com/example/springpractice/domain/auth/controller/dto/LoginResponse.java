package com.example.springpractice.domain.auth.controller.dto;

public record LoginResponse(String accessToken, String tokenType) {

    // 로그인 성공 시 발급된 세션키를 넣어주면 tokenType 은 항상 "Bearer " 로 고정됨
    // 클라이언트가 이 tokenType 을 보고 어떤 형식인지 알 수 있게 해주는 것
    public static LoginResponse from(String accessToken) {
        return new LoginResponse(accessToken, "Bearer ");
    }
}
