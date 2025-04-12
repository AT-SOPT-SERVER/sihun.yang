package org.sopt.assignment.repository;

import org.sopt.assignment.domain.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {
    private final Map<Long, Post> postMap = new HashMap<>();

    public void save(Post post) {
        postMap.put(post.getId(), post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public Post findById(long id) {
        return postMap.get(id);
    }

    public boolean deleteById(long id) {
        return postMap.remove(id) != null;
    }

    public List<Post> findByKeyword(String keyword) {
        List<Post> results = new ArrayList<>();
        for (Post post : postMap.values()) {
            if (post.getTitle().contains(keyword)) {
                results.add(post);
            }
        }
        return results;
    }

    public boolean existsByTitle(String title) {
        return postMap.values().stream()
                .anyMatch(post -> post.getTitle().equals(title));
    }
}
