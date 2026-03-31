package com.example.springpractice.service;

import com.example.springpractice.controller.dto.PostRequest;
import com.example.springpractice.controller.dto.PostResponse;
import com.example.springpractice.entity.PostEntity;
import com.example.springpractice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

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
    // 이건 잘 모르겠다 복습
    public PostResponse findById(Long id) {
        PostEntity entity = postRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("게시글 없음! id=" + id);
        }
        return new PostResponse(entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getContent());
    }

    public void update(Long id, PostRequest request) {
        PostEntity entity = postRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("게시글 없음! id=" + id);
        }
        entity.update(request.getTitle(), request.getContent(), request.getAuthor());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
