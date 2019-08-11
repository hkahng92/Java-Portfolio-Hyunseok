package com.company.postservice.controller;

import com.company.postservice.dao.PostDao;
import com.company.postservice.exception.NotFoundException;
import com.company.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"posts"})
public class PostServiceController {

    @Autowired
    PostDao postDao;

    @CachePut(key = "#result.getPostId()")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody @Valid Post post){
        System.out.println("Creating the Post...");
        return postDao.createPost(post);
    }

    @Cacheable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable("id") int id){
        System.out.println("Getting the Post...");
        Post post = postDao.getPost(id);
        if(post == null){
            throw new NotFoundException("Post cannot be retrieved for the id " + id);
        }
        return post;
    }

    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsForPoster(@PathVariable("poster_name") @Valid String posterName){
        List<Post> posts = postDao.getPostsForPoster(posterName);
        return posts;
    }

    @CacheEvict(key = "#id")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable("id") int id, @RequestBody @Valid Post post){
        if(post.getPostId() == 0){
            post.setPostId(id);
        }
        if(id != post.getPostId()){
            throw new IllegalArgumentException("Cannot Update. Task ID does not exist.");
        }
        postDao.updatePost(post);
    }

    @CacheEvict
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id){
        Post post = postDao.getPost(id);
        if(post == null){
            throw new IllegalArgumentException("Cannot Delete. Post ID does not Exist.");
        }
        postDao.deletePost(id);
    }
}
