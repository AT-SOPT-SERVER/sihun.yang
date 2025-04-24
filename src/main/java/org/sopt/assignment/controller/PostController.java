package org.sopt.assignment.controller;

import jakarta.validation.Valid;
import org.sopt.assignment.dto.request.PostRequest;
import org.sopt.assignment.dto.request.PostUpdateRequest;
import org.sopt.assignment.dto.response.*;
import org.sopt.assignment.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(@RequestBody @Valid final PostRequest postRequest) {
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
    public ResponseEntity<ApiResponse<PostUpdateResponse>> updatePost(
            @PathVariable final Long contentId,
            @RequestBody @Valid final PostUpdateRequest request
    ) {
        PostUpdateResponse updated = postService.updatePostTitle(contentId, request.title());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "게시글이 수정되었습니다.", updated));
    }

    // 전체 게시글 조회
    @GetMapping("/contents")
    public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts() {
        List<PostListItemResponse> posts = postService.getAllPosts().stream()
                .map(post -> new PostListItemResponse(post.getId(), post.getTitle()))
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "전체 게시글이 조회되었습니다.",
                        new PostListResponse(posts)
                ));
    }

    // 게시글 상세 조회
    @GetMapping("/contents/{contentId}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(@PathVariable final Long contentId) {
        PostDetailResponse post = postService.getPostDetail(contentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "게시글 상세 조회",
                        post
                ));
    }

    //게시글 삭제
    @DeleteMapping("/contents/{contentId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable final Long contentId) {
        postService.deletePostById(contentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "게시글이 삭제되었습니다.", null));
    }

    // 게시글 검색
    @GetMapping("/contents/search")
    public ResponseEntity<ApiResponse<PostSearchResponse>> searchPostsByKeyword(
            @RequestParam final String keyword) {
        List<PostSearchItemResponse> results = postService.searchPostsByKeyword(keyword)
                .stream()
                .map(post -> new PostSearchItemResponse(post.getId(), post.getTitle()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "키워드로 게시글 검색 성공",
                        new PostSearchResponse(results)
                ));
    }

}
