package org.sopt.assignment.controller;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.service.PostService;

import java.util.List;

public class PostController {
    private PostService postService  =  new PostService();

    private int postId = 1;


   public void createPost(String title){
        Post post = new Post(postId++, title);
        postService.createPost(post);
    }

    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int id, String newTitle) {
        return postService.updatePostTitle(id, newTitle);
    }


}
