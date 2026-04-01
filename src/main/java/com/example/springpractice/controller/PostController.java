package com.example.springpractice.controller;

import com.example.springpractice.controller.dto.PostRequest;
import com.example.springpractice.controller.dto.PostResponse;
import com.example.springpractice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
/*
@Controller + @ResponseBody 합친게 @RestController
 */
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest request) {
        System.out.println(request);

        PostResponse response = postService.save(request);

        return response;
    }

    @GetMapping
    public List<PostResponse> findAllPosts(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponse findPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
