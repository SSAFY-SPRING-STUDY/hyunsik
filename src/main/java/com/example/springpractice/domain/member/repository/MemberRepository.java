package com.example.springpractice.domain.member.repository;

import com.example.springpractice.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    // ConcurrentHashMap - 멀티스레드 환경에서도 안전하게 쓸 수 있는 Map
    // 일반 HashMap 은 동시에 여러 스레드가 접근하면 문제가 생길 수 있음
    private final Map<Long, MemberEntity> store = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity member) {
        store.put(member.getId(), member);
        return store.get(member.getId());
    }

    // username 으로 전체 순회하면서 찾아줌 - Optional 로 감싸서 null 처리 강제
    public Optional<MemberEntity> findByUsername(String username) {
        for (MemberEntity entity : store.values()) {
            if (entity.getUsername().equals(username)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public Optional<MemberEntity> findById(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }
}
