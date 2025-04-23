package org.sopt.assignment.controller;

import org.sopt.assignment.dto.*;
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
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(@RequestBody final PostRequest postRequest) {
        Long contentId = postService.createPost(postRequest.title());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "게시글이 작성되었습니다.",
                        new PostCreateResponse(contentId)
                ));
    }


    // 게시글 수정
    @PatchMapping("/contents/{contentId}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable final Long contentId,
            @RequestBody final PostUpdateRequest request
    ) {
        PostResponse updated = postService.updatePostTitle(contentId, request.title());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "게시글이 수정되었습니다.", updated));
    }

    // 전체 게시글 조회
    @GetMapping("/contents")
    public ResponseEntity<ApiResponse<PostRetrieveResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts().stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle()))
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "전체 게시글이 조회되었습니다.",
                        new PostRetrieveResponse(posts)
                ));
    }
    }


