package com.company.stwitterservice.service;

import com.company.stwitterservice.model.Comment;
import com.company.stwitterservice.model.Post;
import com.company.stwitterservice.util.feign.CommentServiceClient;
import com.company.stwitterservice.util.feign.PostServiceClient;
import com.company.stwitterservice.viewmodel.StwitterViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class StwitterServiceTest {

    private StwitterService stwitterService;
    private PostServiceClient postServiceClient;
    private CommentServiceClient commentServiceClient;
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() throws Exception {
        setUpRabbitTemplateMock();
        //setUpStwitterService();
        setUpCommentServiceClientMock();
        setUpPostServiceClientMock();

        stwitterService=new StwitterService(rabbitTemplate,commentServiceClient,postServiceClient);
    }
//    private void setUpStwitterService(){
//        stwitterService=new StwitterService(rabbitTemplate,commentServiceClient,postServiceClient);
//    }

    private void setUpRabbitTemplateMock(){
        rabbitTemplate=mock(RabbitTemplate.class);
    }

    private void setUpCommentServiceClientMock(){
        commentServiceClient=mock(CommentServiceClient.class);
        List<Comment> comments = new ArrayList<>();

        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,8,6));
        comment.setCommenterName("LBJ");
        comment.setComment("USA Basketball Dream Team");

        comments.add(comment);

        Comment comment1 = new Comment();
        comment1.setCommentId(2);
        comment1.setPostId(1);
        comment1.setCreateDate(LocalDate.of(2019,8,10));
        comment1.setCommenterName("Kobe");
        comment1.setComment("USA Basketball, the best team in the World!");

        comments.add(comment1);

        doReturn(null).when(commentServiceClient).getCommentsByPostId(2);
        doReturn(comments).when(commentServiceClient).getCommentsByPostId(1);

    }

    private void setUpPostServiceClientMock(){
        postServiceClient=mock(PostServiceClient.class);

        Post post = new Post();
        post.setPostId(1);
        post.setPostDate(LocalDate.of(2018,11,1));
        post.setPosterName("Tyler");
        post.setPost("This post is regarding the day that changed my life.");

        Post post1 = new Post();

        post1.setPostDate(LocalDate.of(2018,11,1));
        post1.setPosterName("Tyler");
        post1.setPost("This post is regarding the day that changed my life.");

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        doReturn(post).when(postServiceClient).createPost(post1);
        doReturn(post).when(postServiceClient).getPost(1);
        doReturn(posts).when(postServiceClient).getPostsForPoster("Tyler");
    }

    @Test
    public void saveFetchStwitter() {
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();

        comment.setCommentId(1);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,8,6));
        comment.setCommenterName("LBJ");
        comment.setComment("USA Basketball Dream Team");
        comments.add(comment);

        Comment comment1 = new Comment();
        comment1.setCommentId(2);
        comment1.setPostId(1);
        comment1.setCreateDate(LocalDate.of(2019,8,10));
        comment1.setCommenterName("Kobe");
        comment1.setComment("USA Basketball, the best team in the World!");
        comments.add(comment1);

        StwitterViewModel stvm = new StwitterViewModel();
        stvm.setPostDate(LocalDate.of(2018,11,1));
        stvm.setPosterName("Tyler");
        stvm.setPost("This post is regarding the day that changed my life.");
        stvm.setComments(comments);

        stvm = stwitterService.saveStwitter(stvm);
        StwitterViewModel fromService = stwitterService.fetchStwitter(stvm.getPostId());
        assertEquals(stvm,fromService);
    }

    @Test
    public void fetchPostForPoster() {
        StwitterViewModel stvm = new StwitterViewModel();
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();

        comment.setCommentId(1);
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,8,6));
        comment.setCommenterName("LBJ");
        comment.setComment("USA Basketball Dream Team");
        comments.add(comment);

        Comment comment1 = new Comment();
        comment1.setCommentId(2);
        comment1.setPostId(1);
        comment1.setCreateDate(LocalDate.of(2019,8,10));
        comment1.setCommenterName("Kobe");
        comment1.setComment("USA Basketball, the best team in the World!");
        comments.add(comment1);

        stvm.setPostDate(LocalDate.of(2018,11,1));
        stvm.setPosterName("Tyler");
        stvm.setPost("This post is regarding the day that changed my life.");
        stvm.setComments(comments);

        stvm = stwitterService.saveStwitter(stvm);

        List<StwitterViewModel> stvmList = stwitterService.fetchPostForPoster("Tyler");
        assertEquals(stvmList.size(),1);
        assertEquals(stvmList.get(0),stvm);
    }

//    @Test
//    public void saveStwitter() {
//    }
}