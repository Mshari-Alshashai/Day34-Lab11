package com.example.day34lab11.Service;

import com.example.day34lab11.ApiResponse.ApiException;
import com.example.day34lab11.Model.Comment;
import com.example.day34lab11.Repository.CommentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment) {
        if (commentRepository.findCommentById(id) == null) throw new ApiException("comment not found");
        Comment oldComment = commentRepository.findCommentById(id);
        oldComment.setContent(comment.getContent());
        oldComment.setPostId(comment.getPostId());
        oldComment.setUserId(comment.getUserId());
        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id) {
        if (commentRepository.findCommentById(id) == null) throw new ApiException("comment not found");
        commentRepository.deleteById(id);
    }

    public List<Comment> findCommentsByAfterDate(LocalDate date) {
        if (commentRepository.findCommentsByAfterDate(date).isEmpty()) throw new ApiException("comments not found");
        return commentRepository.findCommentsByAfterDate(date);
    }

    public Comment findCommentByUserIdAndPostId(Integer userId, Integer postId) {
        if (commentRepository.findCommentByUserIdAndPostId(userId, postId) == null) throw new ApiException("comment not found");
        return commentRepository.findCommentByUserIdAndPostId(userId, postId);
    }
}
