package com.example.day34lab11.Controller;

import com.example.day34lab11.ApiResponse.ApiResponse;
import com.example.day34lab11.Model.Post;
import com.example.day34lab11.Service.PostService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added post"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated post"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted post"));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    @GetMapping("/get-before-date/{date}")
    public ResponseEntity findPostBeforeDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(postService.findPostBeforeDate(date));
    }

}
