package org.sopt.assignment.controller;

import org.sopt.assignment.dto.PostContentResponse;
import org.sopt.assignment.dto.PostRequest;
import org.sopt.assignment.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성
    @PostMapping("/contents")
    public ResponseEntity<PostContentResponse> createPost(@RequestBody final PostRequest postRequest) {
        Long contentId = postService.createPost(postRequest.title());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PostContentResponse(contentId));
    }


}
