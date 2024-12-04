package com.example.day34lab11.Controller;

import com.example.day34lab11.ApiResponse.ApiResponse;
import com.example.day34lab11.Model.Comment;
import com.example.day34lab11.Service.CommentService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("comment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("comment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("comment deleted successfully"));
    }

    @GetMapping("/get-after-date/{date}")
    public ResponseEntity findCommentsByAfterDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(commentService.findCommentsByAfterDate(date));
    }

    @GetMapping("/get-by-user-and-post/{userId}/{postId}")
    public ResponseEntity findCommentByUserIdAndPostId(@PathVariable Integer userId, @PathVariable Integer postId) {
        return ResponseEntity.status(200).body(commentService.findCommentByUserIdAndPostId(userId, postId));
    }
}
