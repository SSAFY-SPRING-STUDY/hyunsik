package com.example.springpractice.controller;

import com.example.springpractice.controller.dto.PostRequest;
import com.example.springpractice.controller.dto.PostResponse;
import com.example.springpractice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/*
@Controller + @RequestBody 합친게 @RestController
 */
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping("/api/posts")
    public PostResponse createPost(@RequestBody PostRequest request) {
        System.out.println(request);


        PostResponse response = postService.save(request);


        return response;
    }
    @GetMapping("/api/posts")
    public List<PostResponse> findAllPosts(){
        return postService.findAll();
    }

    @GetMapping("/api/posts/{id}")
    public PostResponse findPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/api/posts/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
