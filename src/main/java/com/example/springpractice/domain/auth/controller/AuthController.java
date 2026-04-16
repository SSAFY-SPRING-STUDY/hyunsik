package com.example.springpractice.domain.auth.controller;

import com.example.springpractice.domain.auth.controller.dto.LoginRequest;
import com.example.springpractice.domain.auth.controller.dto.LoginResponse;
import com.example.springpractice.domain.auth.service.AuthService;
import com.example.springpractice.domain.auth.util.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // try-catch로 RuntimeException을 잡아 로그인 실패 시 401 반환
    // 예외처리를 상태코드로 처리하기 -> 트라이캐치문 복습
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response = null;
        try {
            response = authService.login(request);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    // @RequestHeader: HTTP 요청 헤더 값을 파라미터로 받는 어노테이션 -> 어노테이션 노트 만들어야함
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String accessToken = AuthorizationUtils.getAccessToken(authHeader);
        authService.logout(accessToken);
        // 204 No Content: 성공했지만 응답 바디가 없을 때 사용
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
