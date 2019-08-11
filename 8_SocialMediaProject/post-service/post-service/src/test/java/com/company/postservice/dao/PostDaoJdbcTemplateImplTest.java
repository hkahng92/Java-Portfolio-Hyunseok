package com.company.postservice.dao;

import com.company.postservice.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostDaoJdbcTemplateImplTest {
    @Autowired
    PostDao postDao;

    @Before
    public void setUp() throws Exception {
        List<Post> posts = postDao.getAllPosts();
        for(Post p : posts){
            postDao.deletePost(p.getPostId());
        }
    }

    @Test
    public void createGetDeletePost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,05,31));
        post.setPosterName("Tyler");
        post.setPost("this is my first post");

        post = postDao.createPost(post);

        Post post1 = postDao.getPost(post.getPostId());

        assertEquals(post,post1);

        postDao.deletePost(post.getPostId());
        post1 = postDao.getPost(post.getPostId());
        assertNull(post1);
    }

    @Test
    public void getAllPosts(){
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,05,31));
        post.setPosterName("Tyler");
        post.setPost("this is my first post");

        postDao.createPost(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019,06,11));
        post.setPosterName("Joe");
        post.setPost("this is the 2nd post");

        postDao.createPost(post);

        List<Post> posts = postDao.getAllPosts();
        assertEquals(posts.size(),2);
    }

    @Test
    public void getPostsForPoster() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,05,31));
        post.setPosterName("Tyler");
        post.setPost("this is my first post");

        postDao.createPost(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019,06,11));
        post.setPosterName("Tyler");
        post.setPost("this is the 2nd post");

        postDao.createPost(post);

        List<Post> postsByPoster = postDao.getPostsForPoster("Tyler");
        assertEquals(postsByPoster.size(),2);
    }

    @Test
    public void updatePost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,05,31));
        post.setPosterName("Tyler");
        post.setPost("this is my first post");

        post = postDao.createPost(post);

        post.setPostDate(LocalDate.of(2019,06,11));
        post.setPosterName("Tyler");
        post.setPost("this is the 2nd post");

        postDao.updatePost(post);

        Post post1 = postDao.getPost(post.getPostId());
        assertEquals(post1,post);
    }
}