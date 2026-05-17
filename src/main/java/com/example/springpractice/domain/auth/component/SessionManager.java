package com.example.springpractice.domain.auth.component;

import com.example.springpractice.global.exception.CustomException;
import com.example.springpractice.global.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// @Component - @Service, @Repository 가 아닌 범용 빈 등록 어노테이션
// 세션 저장소처럼 특정 계층에 속하지 않는 컴포넌트에 사용함
@Component
public class SessionManager {

    // 세션키(UUID 문자열) -> 회원 id 를 매핑해서 저장
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    // 로그인 성공 시 호출 - UUID 로 고유한 세션키 만들고 저장한 뒤 반환
    public String createSession(Long memberId) {
        String sessionKey = UUID.randomUUID().toString();
        sessionStore.put(sessionKey, memberId);
        return sessionKey;
    }

    public void removeSession(String sessionKey) {
        sessionStore.remove(sessionKey);
    }

    // 이전에는 null 반환했는데 이제는 바로 401 예외를 던져버림
    // 컨트롤러에서 null 체크를 따로 안 해도 되게 하려는 것임
    public Long getMemberId(String sessionKey) {
        Long memberId = sessionStore.get(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        return memberId;
    }
}
