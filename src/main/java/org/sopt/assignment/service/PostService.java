package org.sopt.assignment.service;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private LocalDateTime lastCreatedAt = null;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성
    @Transactional
    public Long createPost(String title) {
        LocalDateTime now = LocalDateTime.now();

        Post saved = postRepository.save(new Post(title));
        lastCreatedAt = now;
        return saved.getId();
    }



}
