package com.example.springpractice.post.controller;

import com.example.springpractice.post.controller.dto.PostRequest;
import com.example.springpractice.post.controller.dto.PostResponse;
import com.example.springpractice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController 어노테이션으로 모든 메서드 반환값이 JSON 응답 바디로 직렬화된다
@RestController
@RequiredArgsConstructor
// 이 컨트롤러의 모든 엔드포인트는 api/posts 경로를 기본으로 사용한다
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // ResponseStatus로 성공 시 201 Created를 반환한다
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request) {
        return postService.save(request);
    }

    // 저장된 모든 게시글 목록을 200 OK로 반환한다
    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    // PathVariable로 URL 경로의 id를 받아 해당 게시글을 조회하며 없으면 404를 반환한다
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        try {
            PostResponse response = postService.findById(postId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // id로 게시글을 찾아 제목과 내용을 수정하며 없으면 404를 반환한다
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest request) {
        try {
            PostResponse response = postService.update(request, postId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // id로 게시글을 삭제하고 성공 시 본문 없이 204 No Content를 반환한다
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        try {
            postService.delete(postId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
