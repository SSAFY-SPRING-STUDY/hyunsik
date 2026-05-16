package com.example.springpractice.domain.auth.service;

import com.example.springpractice.domain.auth.component.SessionManager;
import com.example.springpractice.domain.auth.controller.dto.LoginRequest;
import com.example.springpractice.domain.auth.controller.dto.LoginResponse;
import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        // username 으로 회원 조회 - 없으면 RuntimeException 대신 바로 401 던짐
        // try-catch 로 잡지 않아도 Spring 이 알아서 401 응답으로 만들어줌
        MemberEntity member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "아이디가 올바르지 않습니다."));

        // 비밀번호 틀려도 401 - 아이디 없는 경우와 같은 상태코드로 처리해서 어떤 게 틀렸는지 외부에 노출 안 함
        if (!member.checkPassword(request.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다.");
        }

        String sessionKey = sessionManager.createSession(member.getId());
        return LoginResponse.from(sessionKey);
    }

    public void logout(String sessionKey) {
        sessionManager.removeSession(sessionKey);
    }
}
