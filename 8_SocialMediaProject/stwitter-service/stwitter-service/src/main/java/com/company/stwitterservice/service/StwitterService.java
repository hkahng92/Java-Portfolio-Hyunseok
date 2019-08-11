package com.company.stwitterservice.service;

import com.company.stwitterservice.model.Comment;
import com.company.stwitterservice.model.Post;
import com.company.stwitterservice.util.feign.CommentServiceClient;
import com.company.stwitterservice.util.feign.PostServiceClient;
import com.company.stwitterservice.viewmodel.StwitterViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class StwitterService {

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.stwitter.service";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private final CommentServiceClient commentServiceClient;
    @Autowired
    private final PostServiceClient postServiceClient;

    public StwitterService(RabbitTemplate rabbitTemplate, CommentServiceClient commentServiceClient, PostServiceClient postServiceClient){
        this.rabbitTemplate=rabbitTemplate;
        this.commentServiceClient=commentServiceClient;
        this.postServiceClient=postServiceClient;
    }

    @Transactional
    public StwitterViewModel fetchStwitter(int id){
        Post post = postServiceClient.getPost(id);
        return buildStwitterViewModel(post);
    }

    @Transactional
    public List<StwitterViewModel>  fetchPostForPoster(String name){
        List<Post> postList = postServiceClient.getPostsForPoster(name);
        List<StwitterViewModel> stwitterViewModels = new ArrayList<>();

        for(Post post : postList){
            StwitterViewModel stvm = buildStwitterViewModel(post);
            stwitterViewModels.add(stvm);
        }
        return stwitterViewModels;
    }

    @Transactional
    public StwitterViewModel saveStwitter(StwitterViewModel stwitterViewModel){
        Post post = new Post();
        post.setPostDate(stwitterViewModel.getPostDate());
        post.setPosterName(stwitterViewModel.getPosterName());
        post.setPost(stwitterViewModel.getPost());

        post = postServiceClient.createPost(post);
        stwitterViewModel.setPostId(post.getPostId());

        List<Comment> commentList = stwitterViewModel.getComments();
        if(commentList == null){
            return stwitterViewModel;
        } else {
            //this will be comments from the specific postId
            List<Comment> commentList1 = new ArrayList<>();

            commentList.stream()
                    .forEach(comment -> {
                        comment.setPostId(stwitterViewModel.getPostId());
                        commentList1.add(comment);
                    }
                    );

            commentList1.stream()
                    .forEach(comment -> {
                                System.out.println("Sending Comment...");
                                rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,comment);
                                System.out.println("Comment Sent to Queue...");
                    }
                    );

            slowService();

            stwitterViewModel.setComments(commentServiceClient.getCommentsByPostId(stwitterViewModel.getPostId()));
        }
        return stwitterViewModel;
    }


    private StwitterViewModel buildStwitterViewModel(Post post){
        StwitterViewModel stwitterViewModel = new StwitterViewModel();
        stwitterViewModel.setPostId(post.getPostId());
        stwitterViewModel.setPostDate(post.getPostDate());
        stwitterViewModel.setPosterName(post.getPosterName());
        stwitterViewModel.setPost(post.getPost());

        List<Comment> comments = commentServiceClient.getCommentsByPostId(post.getPostId());
        stwitterViewModel.setComments(comments);

        return stwitterViewModel;
    }

    private void slowService() {
        try {
            long time = 1000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


}
