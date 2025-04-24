package org.sopt.assignment.service;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.assignment.domain.Post;
import org.sopt.assignment.dto.PostResponse;
import org.sopt.assignment.exception.ErrorMessage;
import org.sopt.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 하나의 게시글 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.POST_NOT_FOUND.getMessage()));
    }

    // 게시글 전체 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 작성
    @Transactional
    public Long createPost(String title) {
        // 중복 제목 검사
        if (postRepository.existsByTitle(title)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_TITLE.getMessage());
        }

        // 도배 방지
        Post latest = postRepository.findTopByOrderByCreatedAtDesc();
        if (latest != null) {
            Duration duration = Duration.between(latest.getCreatedAt(), LocalDateTime.now());
            if (duration.getSeconds() < 180) {
                throw new IllegalArgumentException(ErrorMessage.SPAM_LIMIT.getMessage());
            }
        }
        Post saved = postRepository.save(new Post(title));
        return saved.getId();
    }

    //게시글 수정
    @Transactional
    public PostResponse updatePostTitle(Long id, String newTitle) {
        Post post = getPostById(id);
        post.updateTitle(newTitle);
        return new PostResponse(post.getId(), post.getTitle());
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public PostResponse getPostDetail(Long id) {
        Post post = getPostById(id);
        return new PostResponse(post.getId(), post.getTitle());
    }

    //게시글 삭제
    @Transactional
    public void deletePostById(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

    //게시글 검색
    @Transactional(readOnly = true)
    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> result = postRepository.searchByKeyword(keyword);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.KEYWORD_NOT_FOUND.getMessage());
        }
        return result;
    }

}
