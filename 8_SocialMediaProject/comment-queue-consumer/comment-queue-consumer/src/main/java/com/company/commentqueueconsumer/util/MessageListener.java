package com.company.commentqueueconsumer.util;

import com.company.commentqueueconsumer.CommentQueueConsumerApplication;
import com.company.commentqueueconsumer.util.feign.CommentServiceClient;
import com.company.commentqueueconsumer.util.message.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private final CommentServiceClient commentServiceClient;

    public MessageListener(CommentServiceClient commentServiceClient){
        this.commentServiceClient=commentServiceClient;
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Comment comment){
        if(comment.getCommentId()==0){
            System.out.println("Sending to the Comment Service to create "+comment.toString());
            commentServiceClient.createComment(comment);
        } else{
            System.out.println("Sending to Comment Service to update "+comment.toString());
            commentServiceClient.updateComment(comment.getCommentId(),comment);
        }
    }
}
