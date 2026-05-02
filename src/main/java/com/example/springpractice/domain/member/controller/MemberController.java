package com.example.springpractice.domain.member.controller;

import com.example.springpractice.domain.auth.component.SessionManager;
import com.example.springpractice.domain.member.controller.dto.MemberRequest;
import com.example.springpractice.domain.member.controller.dto.MemberResponse;
import com.example.springpractice.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    // 컨트롤러에서 세션 유효성을 직접 확인하기 위해 SessionManager를 주입
    private final SessionManager sessionManager;

    // 201 Created: 리소스가 새로 생성됐을 때 사용하는 HTTP 상태코드
    @PostMapping
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request) {
        MemberResponse response = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 내 정보 조회: 토큰으로 회원 id를 찾아 회원 정보를 반환
    // 토큰이 없거나 만료된 경우 401 Unauthorized 반환
    @GetMapping("/me")
    public ResponseEntity<MemberResponse> me(@RequestHeader("Authorization") String authHeader) {
        // "Bearer 토큰값" 에서 앞 7자를 잘라내 순수 토큰만 추출
        String token = authHeader.substring(7);
        Long memberId = sessionManager.getMemberId(token);
        // getMemberId가 null이면 세션이 없는 것이므로 인증 실패
        if (memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(memberService.findById(memberId));
    }
}
