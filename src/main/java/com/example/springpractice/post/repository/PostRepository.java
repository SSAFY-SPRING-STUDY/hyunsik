package com.example.springpractice.post.repository;

import com.example.springpractice.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Repository 어노테이션으로 Spring이 이 클래스를 데이터 접근 계층 빈으로 관리한다
@Repository
public class PostRepository {

    // DB 없이 메모리에서 데이터를 관리하는 인메모리 저장소이다
    private final List<PostEntity> posts = new ArrayList<>();

    // 리스트에 추가 후 저장된 엔티티를 반환한다
    public PostEntity save(PostEntity postEntity) {
        posts.add(postEntity);
        return postEntity;
    }

    // 스트림으로 조건에 맞는 첫 번째 요소를 찾아 Optional로 감싸 반환한다
    public Optional<PostEntity> findById(Long postId) {
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst();
    }

    // 저장된 모든 게시글 리스트를 반환한다
    public List<PostEntity> findAll() {
        return posts;
    }

    // 조건에 맞는 요소를 리스트에서 제거한다
    public void deleteById(Long postId) {
        posts.removeIf(post -> post.getId().equals(postId));
    }
}
