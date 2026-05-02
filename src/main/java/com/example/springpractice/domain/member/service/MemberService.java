package com.example.springpractice.domain.member.service;

import com.example.springpractice.domain.member.controller.dto.MemberRequest;
import com.example.springpractice.domain.member.controller.dto.MemberResponse;
import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // DTO -> Entity 변환 후 저장, Entity -> DTO 변환 후 반환
    // Entity는 절대 노출 x DTO 만 쓰기
    public MemberResponse save(MemberRequest request) {
        MemberEntity entity = MemberRequest.toEntity(request);
        MemberEntity savedEntity = memberRepository.save(entity);
        return MemberResponse.fromEntity(savedEntity);
    }

    // orElseThrow: id에 해당하는 회원이 없으면 예외 발생
    public MemberResponse findById(Long id) {
        MemberEntity entity = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        return MemberResponse.fromEntity(entity);
    }
}
