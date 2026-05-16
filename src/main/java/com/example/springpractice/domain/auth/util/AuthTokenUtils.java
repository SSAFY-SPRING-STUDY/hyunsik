package com.example.springpractice.domain.auth.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// 인스턴스 생성이 필요 없는 유틸 클래스 - private 생성자로 new 하지 못하게 막아둠
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthTokenUtils {

    public static final String PREFIX_BEARER = "Bearer ";

    // null 이거나 "Bearer " 로 시작하지 않으면 true 반환 - 유효하지 않다는 의미
    // 컨트롤러에서 if (isValidBearerToken(token)) 으로 체크하고 401 처리하면 됨
    public static boolean isValidBearerToken(String bearerToken) {
        return bearerToken == null || !bearerToken.startsWith(PREFIX_BEARER);
    }

    // "Bearer " 앞부분 잘라내고 순수 세션키만 꺼내줌
    // PREFIX_BEARER.length() 를 쓰면 길이 하드코딩 안 해도 됨
    public static String parseBearerToken(String bearerToken) {
        return bearerToken.substring(PREFIX_BEARER.length());
    }
}
