package org.sopt.assignment.service;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.dto.PostResponse;
import org.sopt.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository postRepository;
    private LocalDateTime lastCreatedAt = null;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 하나의 게시글 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));
    }

    // 게시글 작성
    @Transactional
    public Long createPost(String title) {
        LocalDateTime now = LocalDateTime.now();

        Post saved = postRepository.save(new Post(title));
        lastCreatedAt = now;
        return saved.getId();
    }

    //게시글 수정
    @Transactional
    public PostResponse updatePostTitle(Long id, String newTitle) {
        Post post = getPostById(id); // 존재하지 않으면 예외 발생
        post.updateTitle(newTitle);
        return new PostResponse(post.getId(), post.getTitle());
    }




}
