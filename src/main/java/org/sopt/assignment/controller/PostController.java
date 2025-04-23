package org.sopt.assignment.controller;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.dto.PostRequest;
import org.sopt.assignment.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.getTitle());
        return ResponseEntity.ok().build();
    }

}
