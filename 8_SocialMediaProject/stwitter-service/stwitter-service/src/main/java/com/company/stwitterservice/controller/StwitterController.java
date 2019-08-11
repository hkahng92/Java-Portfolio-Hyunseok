package com.company.stwitterservice.controller;

import com.company.stwitterservice.exception.NotFoundException;
import com.company.stwitterservice.model.Post;
import com.company.stwitterservice.service.StwitterService;
import com.company.stwitterservice.viewmodel.StwitterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class StwitterController {

    @Autowired
    StwitterService service;

    @RequestMapping(value = "/posts/{id}",method= RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public StwitterViewModel getPost(@PathVariable("id") int id){
        StwitterViewModel pvm = service.fetchStwitter(id);
        if(pvm == null){
            throw new NotFoundException("Post cannot be retrieved for the id " + id);
        }
        return pvm;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public StwitterViewModel createPost(@RequestBody @Valid StwitterViewModel stwitterViewModel){

        return service.saveStwitter(stwitterViewModel);
    }

    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    public List<StwitterViewModel> getPostsForPoster(@PathVariable("poster_name") @Valid String posterName){
        List<StwitterViewModel> pvms = service.fetchPostForPoster(posterName);
        if(pvms != null && pvms.size()==0){
            throw new NotFoundException("Post(s) cannot be retrieved by the poster name: " + posterName);
        }
        return pvms;
    }
}
