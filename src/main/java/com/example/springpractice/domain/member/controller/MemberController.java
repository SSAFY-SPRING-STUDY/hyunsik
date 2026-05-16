package com.example.springpractice.domain.member.controller;

import com.example.springpractice.domain.auth.component.SessionManager;
import com.example.springpractice.domain.auth.util.AuthTokenUtils;
import com.example.springpractice.domain.member.controller.dto.MemberRequest;
import com.example.springpractice.domain.member.controller.dto.MemberResponse;
import com.example.springpractice.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final SessionManager sessionManager;

    // 201 Created - 리소스가 새로 생성됐을 때 사용하는 HTTP 상태코드
    @PostMapping
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request) {
        MemberResponse response = memberService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        // 토큰 형식 먼저 검사 - null 이거나 "Bearer " 로 시작 안 하면 바로 401
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 형식입니다.");
        }
        // "Bearer " 잘라내고 세션키 추출 -> 세션키로 회원 id 조회 -> 회원 정보 반환
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);
        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }
}
