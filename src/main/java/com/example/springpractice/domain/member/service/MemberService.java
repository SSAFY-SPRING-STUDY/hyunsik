package com.example.springpractice.domain.member.service;

import com.example.springpractice.domain.member.controller.dto.MemberRequest;
import com.example.springpractice.domain.member.controller.dto.MemberResponse;
import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.member.repository.MemberRepository;
import com.example.springpractice.global.exception.CustomException;
import com.example.springpractice.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    // id 로 회원 못 찾으면 404 - CustomException으로 ErrorCode에 맞는 응답을 내려준다
    public MemberResponse getMemberInfo(Long memberId) {
        MemberEntity entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.from(entity);
    }
}
