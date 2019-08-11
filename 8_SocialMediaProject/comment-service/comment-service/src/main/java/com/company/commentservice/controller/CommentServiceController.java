package com.company.commentservice.controller;

import com.company.commentservice.dao.CommentDao;
import com.company.commentservice.exception.NotFoundException;
import com.company.commentservice.model.Comment;
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
@CacheConfig(cacheNames = {"comments"})
public class CommentServiceController {

    @Autowired
    CommentDao commentDao;

    @CachePut(key = "#result.getCommentId()")
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody @Valid Comment comment){
        System.out.println("Creating the Comment...");
        return commentDao.createComment(comment);
    }

    @Cacheable
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Comment getComment(@PathVariable("id") int id){
        System.out.println("Getting the Comment...");
        Comment comment = commentDao.getComment(id);
        if(comment == null){
            throw new NotFoundException("Comment cannot be retrieved for the id " + id);
        }
        return comment;
    }

    @CacheEvict(key = "#id")
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable("id") int id, @RequestBody @Valid Comment comment){
        if(comment.getCommentId() == 0){
            comment.setCommentId(id);
        }
        if(id != comment.getCommentId()){
            throw new IllegalArgumentException("Cannot Update. Task ID does not exist.");
        }
        commentDao.updateComment(comment);
    }

    @CacheEvict
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") int id){
        Comment comment = commentDao.getComment(id);
        if(comment == null){
            throw new IllegalArgumentException("Cannot Delete. Comment ID does not Exist.");
        }
        commentDao.deleteComment(id);
    }

    @RequestMapping(value = "/comments/post/{post_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findCommentsByPostId(@PathVariable("post_id") int id){
        List<Comment> commentList = commentDao.getCommentsByPostId(id);
        return commentList;
    }


}
