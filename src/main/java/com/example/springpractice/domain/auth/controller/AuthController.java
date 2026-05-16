package com.example.springpractice.domain.auth.controller;

import com.example.springpractice.domain.auth.controller.dto.LoginRequest;
import com.example.springpractice.domain.auth.controller.dto.LoginResponse;
import com.example.springpractice.domain.auth.service.AuthService;
import com.example.springpractice.domain.auth.util.AuthTokenUtils;
import com.example.springpractice.global.exception.CustomException;
import com.example.springpractice.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // 로그인 실패 시 AuthService 에서 CustomException(401) 을 던지므로
    // 이전처럼 try-catch 로 잡을 필요 없이 GlobalExceptionHandler가 응답으로 바꿔준다
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // @RequestHeader - HTTP 요청 헤더 값을 파라미터로 받는 어노테이션
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "Authorization", required = false) String bearerToken) {
        // 토큰 형식 잘못됐으면 로그아웃도 막아줌
        if (!AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        authService.logout(sessionKey);
        // 204 No Content - 성공했지만 응답 바디가 없을 때 사용
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
