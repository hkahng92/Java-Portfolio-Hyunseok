package com.company.stwitterservice.util.feign;

import com.company.stwitterservice.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "post-service")
public interface PostServiceClient {

    @RequestMapping(value = "/posts/{id}",method= RequestMethod.GET)
    public Post getPost(@PathVariable("id") int id);

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public Post createPost(@RequestBody @Valid Post post);

    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    public List<Post> getPostsForPoster(@PathVariable("poster_name") @Valid String posterName);

    }
