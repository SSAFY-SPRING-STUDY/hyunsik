package com.example.springpractice.domain.auth.service;


import com.example.springpractice.domain.auth.component.SessionManager;
import com.example.springpractice.domain.auth.controller.dto.LoginRequest;
import com.example.springpractice.domain.auth.controller.dto.LoginResponse;
import com.example.springpractice.domain.auth.util.AuthorizationUtils;
import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    // loginrequest 받아올떄 아이디 패스워드 받잔슴?
    // MemberEntity 에 저장된 거 조회, 올바르면 로그인!
    // 근데 멤버에 있으니까 로그인된 상태를 저장해서 token 형태로 확인함.

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findbyLoginId(request.loginId())
                .orElseThrow(() -> new RuntimeException("아이디가 올바르지 않습니다."));

        if(member.isValidPassword(request.password())){
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token, AuthorizationUtils.BEARER_PREFIX.strip());
            // BEARER 가 뭐지 -> 토큰 타입 근데 이거 소문자로 써야한다함 대문자 x

        }
        throw new RuntimeException("비밀번호가 올바르지 않습니다.");


    }

    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }
}
