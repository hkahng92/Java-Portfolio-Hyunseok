package com.company.commentservice.dao;

import com.company.commentservice.model.Comment;
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
public class CommentDaoJdbcTemplateImplTest {

    @Autowired
    CommentDao commentDao;

    @Before
    public void setUp() throws Exception {
        List<Comment> comments = commentDao.getAllComments();
        for(Comment comment : comments){
            commentDao.deleteComment(comment.getCommentId());
        }
    }

    @Test
    public void createGetDeleteComment() {
        Comment comment = new Comment();

        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,05,31));
        comment.setCommenterName("Tyler");
        comment.setComment("This is my 1st comment");

        comment = commentDao.createComment(comment);

        Comment comment1 = commentDao.getComment(comment.getCommentId());
        assertEquals(comment,comment1);

        commentDao.deleteComment(comment.getCommentId());
        comment1 = commentDao.getComment(comment.getCommentId());
        assertNull(comment1);
    }

    @Test
    public void getAllComments() {
        Comment comment = new Comment();

        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,05,31));
        comment.setCommenterName("Tyler");
        comment.setComment("This is my 1st comment");

        commentDao.createComment(comment);

        comment = new Comment();
        comment.setPostId(2);
        comment.setCreateDate(LocalDate.of(2019,03,11));
        comment.setCommenterName("Tim");
        comment.setComment("This is Your 1st comment");

        commentDao.createComment(comment);

        List<Comment> comments = commentDao.getAllComments();
        assertEquals(comments.size(),2);
    }

    @Test
    public void updateComment() {
        Comment comment = new Comment();

        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,05,31));
        comment.setCommenterName("Tyler");
        comment.setComment("This is my 1st comment");

        comment = commentDao.createComment(comment);

        comment.setPostId(2);
        comment.setCreateDate(LocalDate.of(2019,03,11));
        comment.setCommenterName("Tim");
        comment.setComment("This is Your 1st comment");

        commentDao.updateComment(comment);

        Comment comment1 = commentDao.getComment(comment.getCommentId());
        assertEquals(comment1,comment);
    }

    @Test
    public void getCommentsByPostId() {
        Comment comment = new Comment();

        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,05,31));
        comment.setCommenterName("Tyler");
        comment.setComment("This is my 1st comment");

        commentDao.createComment(comment);

        comment = new Comment();
        comment.setPostId(1);
        comment.setCreateDate(LocalDate.of(2019,03,11));
        comment.setCommenterName("Tim");
        comment.setComment("This is Your 1st comment");

        commentDao.createComment(comment);

        List<Comment> comments = commentDao.getCommentsByPostId(1);
        assertEquals(comments.size(),2);
    }
}