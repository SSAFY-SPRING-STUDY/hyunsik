package com.example.springpractice.domain.auth.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// 얘는 왜 객체가 없나? 보조이기 때문. 유틸리티 클래스는 static 메서드만 모아두므로 객체를 만들 필요가 없음
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationUtils {

    // Authorization 헤더값에서 "Bearer " 접두어를 제거하고 순수 토큰만 반환
    // startsWith로 올바른 형식인지 먼저 확인한 뒤 substring(7)로 잘라냄 왜 7임?

    public static String getAccessToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
            // 토큰은 7번째 뒤부터 시작
        }
        throw new IllegalArgumentException("토큰에 문제가 있습니다.");
    }
}
