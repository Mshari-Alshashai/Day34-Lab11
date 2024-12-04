package com.example.day34lab11.Repository;

import com.example.day34lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    @Query("select c from Comment c where c.commentDate>?1")
    List<Comment> findCommentsByAfterDate(LocalDate commentDate);

    Comment findCommentByUserIdAndPostId(Integer userId, Integer postId);
}
