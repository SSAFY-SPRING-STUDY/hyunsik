package com.example.springpractice.domain.auth.controller.dto;

public record LoginRequest(
        // 레코드는 아직 잘 모르겟음 최신문법이라던데
        String loginId,
        String password
) {
}
