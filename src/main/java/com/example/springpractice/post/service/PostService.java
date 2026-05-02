package com.example.springpractice.post.service;

import com.example.springpractice.post.controller.dto.PostRequest;
import com.example.springpractice.post.controller.dto.PostResponse;
import com.example.springpractice.post.entity.PostEntity;
import com.example.springpractice.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Service 어노테이션으로 Spring이 비즈니스 로직을 담당하는 빈으로 관리한다
@Service
// RequiredArgsConstructor가 final 필드를 대상으로 생성자를 자동 생성하여 의존성을 주입한다
@RequiredArgsConstructor // 생성자랑 @Autowired 대체한다
public class PostService {

    private final PostRepository postRepository;

    // 요청 데이터로 엔티티를 생성하고 저장 후 응답 DTO로 변환해 반환한다
    public PostResponse save(PostRequest request) {
        PostEntity postEntity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());
        PostEntity saved = postRepository.save(postEntity);
        return PostResponse.from(saved);
    }

    // 전체 게시글을 스트림으로 변환하여 응답 DTO 리스트로 반환한다
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    // 게시글이 없으면 RuntimeException을 던지고 컨트롤러에서 404로 처리된다
    public PostResponse findById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return PostResponse.from(postEntity);
    }

    // 게시글을 찾아 제목과 내용을 수정하고 변경된 내용을 응답 DTO로 반환한다
    public PostResponse update(PostRequest request, Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        postEntity.update(request.getTitle(), request.getContent());
        return PostResponse.from(postEntity);
    }

    // 저장소에서 해당 id의 게시글을 삭제한다
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
