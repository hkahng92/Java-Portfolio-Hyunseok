package com.company.commentservice.dao;

import com.company.commentservice.model.Comment;

import java.util.List;

public interface CommentDao {
   Comment getComment(int id);
   List<Comment> getAllComments();
   List<Comment> getCommentsByPostId(int id);
   Comment createComment(Comment comment);
   Comment updateComment(Comment comment);
   void deleteComment(int id);
}
