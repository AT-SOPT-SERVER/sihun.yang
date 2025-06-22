package org.sopt.assignment.controller;

import jakarta.validation.Valid;
import org.sopt.assignment.dto.*;
import org.sopt.assignment.dto.request.PostRequest;
import org.sopt.assignment.dto.request.PostUpdateRequest;
import org.sopt.assignment.dto.response.*;
import org.sopt.assignment.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sopt.assignment.domain.Tag;

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
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(
            @RequestBody @Valid final PostRequest postRequest,
            @RequestHeader("userId") Long userId) {
        Long postId = postService.createPost(postRequest.title(),postRequest.content(),userId, postRequest.tag());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "게시글이 작성되었습니다.",
                        new PostCreateResponse(postId)
                ));
    }

    // 게시글 수정
    @PatchMapping("/contents/{postId}")
    public ResponseEntity<ApiResponse<PostUpdateResponse>> updatePost(
            @PathVariable final Long postId,
            @RequestHeader("userId") Long userId,
            @RequestBody @Valid final PostUpdateRequest request
    ) {
        PostUpdateResponse updated = postService.updatePost(postId, userId, request.title(), request.content(), request.tag());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "게시글이 수정되었습니다.", updated));
    }

    // 전체 게시글 조회
    @GetMapping("/contents")
    public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts() {
        List<PostListItemResponse> posts = postService.getAllPosts().stream()
                .map(post -> new PostListItemResponse(post.getId(), post.getTitle(),post.getUser().getNickname(), post.getTag()))
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
    @GetMapping("/contents/{postId}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(@PathVariable final Long postId) {
        PostDetailResponse post = postService.getPostDetail(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "게시글 상세 조회",
                        post
                ));
    }

    //게시글 삭제
    @DeleteMapping("/contents/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable final Long postId,
            @RequestHeader("userId") Long userId
    ) {
        postService.deletePostById(postId, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "게시글이 삭제되었습니다.", null));
    }

    //게시글 검색 (제목 기준)
    @GetMapping("/contents/search")
    public ResponseEntity<ApiResponse<PostSearchResponse>> searchPostsByKeyword(
            @RequestParam final String keyword) {

        List<PostSearchItemResponse> results = postService.searchPostsByKeyword(keyword)
                .stream()
                .map(post -> new PostSearchItemResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getNickname(),
                        post.getTag()
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "키워드로 게시글 검색 성공",
                        new PostSearchResponse(results)
                ));
    }

    //게시글 검색 (작성자 기준)
    @GetMapping("/contents/search/writer")
    public ResponseEntity<ApiResponse<PostSearchResponse>> searchPostsByWriter(
            @RequestParam final String nickname) {

        List<PostSearchItemResponse> results = postService.searchPostsByWriter(nickname)
                .stream()
                .map(post -> new PostSearchItemResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getNickname(),
                        post.getTag()
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "작성자 닉네임으로 게시글 검색 성공",
                        new PostSearchResponse(results)
                ));
    }

    //게시글 검색 (태그 기준)
    @GetMapping("/contents/search/tag")
    public ResponseEntity<ApiResponse<PostSearchResponse>> searchByTag(@RequestParam Tag tag) {
        List<PostSearchItemResponse> results = postService.searchByTag(tag).stream()
                .map(post -> new PostSearchItemResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getNickname(),
                        post.getTag()
                ))
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "태그로 검색 성공",
                new PostSearchResponse(results)
        ));
    }


}
