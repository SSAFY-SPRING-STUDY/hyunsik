package com.example.springpractice.domain.member.service;

import com.example.springpractice.domain.member.controller.dto.MemberRequest;
import com.example.springpractice.domain.member.controller.dto.MemberResponse;
import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입 - request.toEntity() 로 entity 만들고 저장, 결과를 DTO 로 변환해서 반환
    public MemberResponse join(MemberRequest request) {
        MemberEntity entity = request.toEntity();
        MemberEntity savedEntity = memberRepository.save(entity);
        return MemberResponse.from(savedEntity);
    }

    // id 로 회원 못 찾으면 404 - RuntimeException 대신 ResponseStatusException 으로 HTTP 상태코드 직접 지정
    public MemberResponse getMemberInfo(Long memberId) {
        MemberEntity entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."));
        return MemberResponse.from(entity);
    }
}
