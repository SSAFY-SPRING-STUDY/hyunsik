package com.example.springpractice.domain.post.controller;

import com.example.springpractice.domain.auth.component.SessionManager;
import com.example.springpractice.domain.auth.util.AuthTokenUtils;
import com.example.springpractice.domain.post.dto.PostRequest;
import com.example.springpractice.domain.post.dto.PostResponse;
import com.example.springpractice.domain.post.service.PostService;
import com.example.springpractice.global.exception.CustomException;
import com.example.springpractice.global.exception.error.ErrorCode;
import com.example.springpractice.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final SessionManager sessionManager;

    // @RequestHeader로 뭘 하지?? -> HTTP 요청 헤더에서 특정 값을 꺼낸다
    // Authorization 헤더 값 형식은?? -> "Bearer {세션키}" 형태로 온다

    // 토큰 검증은 왜 컨트롤러에서 하지?? -> 요청 자체가 유효한지는 웹 계층에서 판단해야 하니까
    // 권한 검증은 왜 서비스에서 하지?? -> 유효한 요청이 허용된 작업인지는 비즈니스 규칙이니까
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestBody PostRequest request,
            @RequestHeader(value = "Authorization", required = false) String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);
        PostResponse result = postService.create(request, authorId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("게시글 생성 성공", result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts() {
        List<PostResponse> result = postService.findAll();
        return ResponseEntity
                .ok(ApiResponse.success("게시글 목록 조회 성공", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@PathVariable Long id) {
        PostResponse result = postService.getPostById(id);
        return ResponseEntity
                .ok(ApiResponse.success("게시글 조회 성공", result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequest request,
            @RequestHeader(value = "Authorization", required = false) String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);
        PostResponse result = postService.update(request, id, authorId);
        return ResponseEntity
                .ok(ApiResponse.success("게시글 수정 성공", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);
        postService.delete(id, authorId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("게시글 삭제 성공"));
    }
}
