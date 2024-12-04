package com.example.day34lab11.Service;

import com.example.day34lab11.ApiResponse.ApiException;
import com.example.day34lab11.Model.Post;
import com.example.day34lab11.Repository.PostRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post) {
        if (postRepository.findPostById(id) == null) throw new ApiException("post not found");
        Post oldPost = postRepository.findPostById(id);
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setUserId(post.getUserId());
        oldPost.setCategoryId(post.getCategoryId());
        postRepository.save(oldPost);
    }

    public void deletePost(Integer id) {
        if (postRepository.findPostById(id) == null) throw new ApiException("post not found");
        postRepository.deleteById(id);
    }

    public Post getPostByTitle(String title) {
        if (postRepository.findPostByTitle(title) == null) throw new ApiException("post not found");
        return postRepository.findPostByTitle(title);
    }

    public List<Post> findPostBeforeDate(LocalDate date) {
        if (postRepository.findPostBeforeDate(date).isEmpty()) throw new ApiException("post not found");
        return postRepository.findPostBeforeDate(date);
    }
}
