package org.sopt.assignment.service;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.repository.PostRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private LocalDateTime lastCreatedAt = null;

    public void createPost(Post post) {
        // 도배 방지
        LocalDateTime now = LocalDateTime.now();
        if (lastCreatedAt != null) {
            long seconds = Duration.between(lastCreatedAt, now).getSeconds();
            if (seconds < 180) {
                throw new IllegalArgumentException("3분동안 게시글 작성이 제한됩니다.");
            }
        }
        //중복 제목 검증
        if(postRepository.existsByTitle(post.getTitle())) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다.");
        }
        postRepository.save(post);
        lastCreatedAt = now;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.deleteById(id);
    }

    public boolean updatePostTitle(int id, String newTitle) {
        Post post = postRepository.findById(id);
        if (post == null) {
            return false;
        }
        post.updateTitle(newTitle);
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByKeyword(keyword);
    }

}
