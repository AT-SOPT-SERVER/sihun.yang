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

    // 게시글 생성
    @Transactional
    public void createPost(String title) {
        LocalDateTime now = LocalDateTime.now();

        // 도배 방지
        if (lastCreatedAt != null && Duration.between(lastCreatedAt, now).getSeconds() < 180) {
            throw new IllegalArgumentException("3분 이내에는 게시글을 다시 작성할 수 없습니다.");
        }

        // 제목 중복 체크
        if (postRepository.findAll().stream().anyMatch(p -> p.getTitle().equals(title))) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다.");
        }

        postRepository.save(new Post(title));
        lastCreatedAt = now;
    }

    // 전체 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 단건 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));
    }

    // 삭제
    @Transactional
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    // 제목 수정
    @Transactional
    public void updatePostTitle(Long id, String newTitle) {
        Post post = getPostById(id); // 위에서 예외 처리됨
        post.updateTitle(newTitle);  // 도메인 메서드
    }

    // 키워드 검색
    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findAll().stream()
                .filter(post -> post.getTitle().contains(keyword))
                .toList();
    }
}
