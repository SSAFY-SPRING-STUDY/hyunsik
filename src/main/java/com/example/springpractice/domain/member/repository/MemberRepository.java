package com.example.springpractice.domain.member.repository;

import com.example.springpractice.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    // 멀티스레드 고려 컨커해쉬맵
    private static Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity entity) {
        memberStore.put(entity.getId(), entity);
        MemberEntity savedEntity = memberStore.get(entity.getId());

        return savedEntity;
    }
    // 옵셔널 복습하기..
    public Optional<MemberEntity> findbyLoginId(String loginId) {
        for (MemberEntity entity : memberStore.values()) {
            if (entity.getLoginId().equals(loginId))
                return Optional.of(entity);
        }
        return Optional.empty();
    }

    public Optional<MemberEntity> findById(Long id) {
        return Optional.ofNullable(memberStore.get(id));
    }
}
