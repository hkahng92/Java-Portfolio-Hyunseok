package com.company.postservice.dao;

import com.company.postservice.model.Post;

import java.util.List;

public interface PostDao {

    Post createPost(Post post);

    Post getPost(int id);

    List<Post> getAllPosts();

    List<Post> getPostsForPoster(String posterName);

    Post updatePost(Post post);

    void deletePost(int id);
}
