package com.example.springpractice.domain.post.service;

import com.example.springpractice.domain.member.entity.MemberEntity;
import com.example.springpractice.domain.member.repository.MemberRepository;
import com.example.springpractice.domain.post.entity.PostEntity;
import com.example.springpractice.domain.post.dto.PostRequest;
import com.example.springpractice.domain.post.dto.PostResponse;
import com.example.springpractice.domain.post.repository.PostRepository;
import com.example.springpractice.global.exception.CustomException;
import com.example.springpractice.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // authorId로 회원을 먼저 조회하는 이유?? -> 세션에 id가 있어도 탈퇴한 회원일 수 있으니까
    // CustomException을 던지면 어디서 잡지?? -> GlobalExceptionHandler가 잡아서 응답으로 만들어준다
    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity entity = request.toEntity(member);
        PostEntity saved = postRepository.save(entity);
        return PostResponse.from(saved);
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(entity);
    }

    // 권한 검증을 왜 서비스에서 하지?? -> 컨트롤러는 요청과 응답만 담당하고 비즈니스 규칙은 서비스 책임이니까
    // throw 뒤에 else가 없는 이유?? -> throw 이후 코드는 실행되지 않으니까 바로 이어서 쓰면 된다
    public PostResponse update(PostRequest request, Long id, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        post.update(request.getTitle(), request.getContent());
        return PostResponse.from(post);
    }

    public void delete(Long id, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        postRepository.deleteById(id);
    }
}
