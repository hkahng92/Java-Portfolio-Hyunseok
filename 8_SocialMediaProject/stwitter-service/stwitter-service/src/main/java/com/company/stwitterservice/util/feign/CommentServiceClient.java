package com.company.stwitterservice.util.feign;

import com.company.stwitterservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name ="comment-service")
public interface CommentServiceClient {

    @RequestMapping(value = "/comments/post/{post_id}",method= RequestMethod.GET)
    public List<Comment> getCommentsByPostId(@PathVariable("post_id") int postId);
}
