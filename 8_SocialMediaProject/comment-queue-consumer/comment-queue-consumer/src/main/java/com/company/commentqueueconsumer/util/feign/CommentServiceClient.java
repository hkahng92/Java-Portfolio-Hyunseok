package com.company.commentqueueconsumer.util.feign;

import com.company.commentqueueconsumer.util.message.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "comment-service")
public interface CommentServiceClient {

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public Comment createComment(Comment comment);

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    public void updateComment(@PathVariable("id") int id,Comment comment);

    @RequestMapping(value = "/comments/{id}", method=RequestMethod.DELETE)
    public void deleteComment(@PathVariable("id") int id);

    @RequestMapping(value = "/comments/post/{id}", method=RequestMethod.GET)
    public List<Comment> findCommentsByPostId(@PathVariable("id") int id);
}
