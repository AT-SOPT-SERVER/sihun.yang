package org.sopt.assignment.controller;

import jakarta.validation.Valid;
import org.sopt.assignment.dto.*;
import org.sopt.assignment.dto.request.PostRequest;
import org.sopt.assignment.dto.request.PostUpdateRequest;
import org.sopt.assignment.dto.response.*;
import org.sopt.assignment.service.PostService;
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

    @PostMapping("/contents")
    public ApiResponse<PostCreateResponse> createPost(
            @RequestBody @Valid final PostRequest postRequest,
            @RequestHeader("userId") Long userId) {
        Long postId = postService.createPost(postRequest.title(),postRequest.content(),userId, postRequest.tag());
        return new ApiResponse<>(201, "게시글이 작성되었습니다.", new PostCreateResponse(postId));
    }

    @PatchMapping("/contents/{postId}")
    public ApiResponse<PostUpdateResponse> updatePost(
            @PathVariable final Long postId,
            @RequestHeader("userId") Long userId,
            @RequestBody @Valid final PostUpdateRequest request
    ) {
        PostUpdateResponse updated = postService.updatePost(postId, userId, request.title(), request.content(), request.tag());
        return new ApiResponse<>(200, "게시글이 수정되었습니다.", updated);
    }

    @GetMapping("/contents")
    public ApiResponse<PostListResponse> getAllPosts() {
        List<PostListItemResponse> posts = postService.getAllPosts().stream()
                .map(post -> new PostListItemResponse(post.getId(), post.getTitle(),post.getUser().getNickname(), post.getTag()))
                .toList();
        return new ApiResponse<>(200, "전체 게시글이 조회되었습니다.", new PostListResponse(posts));
    }

    @GetMapping("/contents/{postId}")
    public ApiResponse<PostDetailResponse> getPostById(@PathVariable final Long postId) {
        PostDetailResponse post = postService.getPostDetail(postId);
        return new ApiResponse<>(200, "게시글 상세 조회", post);
    }

    @DeleteMapping("/contents/{postId}")
    public ApiResponse<Void> deletePost(
            @PathVariable final Long postId,
            @RequestHeader("userId") Long userId
    ) {
        postService.deletePostById(postId, userId);
        return new ApiResponse<>(200, "게시글이 삭제되었습니다.", null);
    }

    @GetMapping("/contents/search")
    public ApiResponse<PostSearchResponse> searchPostsByKeyword(
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
        return new ApiResponse<>(200, "키워드로 게시글 검색 성공", new PostSearchResponse(results));
    }

    @GetMapping("/contents/search/writer")
    public ApiResponse<PostSearchResponse> searchPostsByWriter(
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
        return new ApiResponse<>(200, "작성자 닉네임으로 게시글 검색 성공", new PostSearchResponse(results));
    }

    @GetMapping("/contents/search/tag")
    public ApiResponse<PostSearchResponse> searchByTag(@RequestParam Tag tag) {
        List<PostSearchItemResponse> results = postService.searchByTag(tag).stream()
                .map(post -> new PostSearchItemResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getNickname(),
                        post.getTag()
                ))
                .toList();
        return new ApiResponse<>(200, "태그로 검색 성공", new PostSearchResponse(results));
    }
}
