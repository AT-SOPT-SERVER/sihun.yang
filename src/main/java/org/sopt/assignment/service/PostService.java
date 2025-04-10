package org.sopt.assignment.service;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public void createPost(Post post) {
        if(postRepository.existsByTitle(post.getTitle())) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다.");
        }
        postRepository.save(post);
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
        post.setTitle(newTitle);
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByKeyword(keyword);
    }

}
