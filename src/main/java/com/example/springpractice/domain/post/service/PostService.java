package com.example.springpractice.domain.post.service;

import com.example.springpractice.domain.post.dto.PostRequest;
import com.example.springpractice.domain.post.dto.PostResponse;
import com.example.springpractice.domain.post.controller.entity.PostEntity;
import com.example.springpractice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public PostResponse save(PostRequest request){
        PostEntity entity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());

        PostEntity returnedEntity = postRepository.save(entity);

        PostResponse response = new PostResponse(returnedEntity.getId(),
                returnedEntity.getTitle(),
                returnedEntity.getAuthor(),
                returnedEntity.getContent());

        return response;
    }
    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for (PostEntity entity : entityList) {
            PostResponse response = new PostResponse(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getAuthor(),
                    entity.getContent());
            responseList.add(response);
        }

        return responseList;
    }
    public PostResponse findById(Long id) {
        Optional<PostEntity> optional = postRepository.findById(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("게시글 없음! id=" + id);
        }
        PostEntity entity = optional.get();
        return new PostResponse(entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getContent());
    }

    public void update(Long id, PostRequest request) {
        Optional<PostEntity> optional = postRepository.findById(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("게시글 없음! id=" + id);
        }
        PostEntity entity = optional.get();
        entity.update(request.getTitle(), request.getContent(), request.getAuthor());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
