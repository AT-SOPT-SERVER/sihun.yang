package org.sopt.assignment.repository;

import org.sopt.assignment.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findById(int id) {
        for (Post post : postList) {
            if (post.getId() == id)
                return post;
        }
        return null;
    }

    public boolean deleteById(int id) {
        return postList.removeIf(post -> post.getId() == id);
    }

}
