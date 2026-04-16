package com.example.springpractice.domain.auth.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// @Component: @Service, @Repository가 아닌 범용 빈 등록 어노테이션
// 세션 저장소처럼 특정 계층에 속하지 않는 컴포넌트에 사용(service,repository 는 기본적으로 component임)
@Component
public class SessionManager {

    // 실무에서는 DB를 사용하지만, 여기서는 Map으로 대체
    // ConcurrentHashMap 공부할 것 : ArrayList랑 무슨차이인가?
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    // 로그인 성공 시 호출: 랜덤 UUID 토큰을 생성해 세션에 저장하고 반환
    // UUID는 충돌 가능성이 거의 없는 고유 식별자 문자열 (해쉬?)
    public String createSession(Long id) {
        String token = UUID.randomUUID().toString();
        sessionStore.put(token, id);
        return token;
    }

    // 토큰으로 회원 id 조회: 토큰이 없으면 null 반환
    // null이면 로그인되지 않은 상태(또는 만료된 세션)로 판단
    public Long getMemberId(String token) {
        return sessionStore.get(token);
    }

    // 로그아웃 시 호출: 세션 저장소에서 토큰 삭제
    // 삭제 후에는 해당 토큰으로 getMemberId를 호출하면 null이 반환됨
    public void removeSession(String token) {
        sessionStore.remove(token);
    }
}
