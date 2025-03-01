package com.example.day34lab11.Repository;

import com.example.day34lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    Post findPostByTitle(String title);

    @Query("select p from Post p where p.publishDate < ?1")
    List<Post> findPostBeforeDate(LocalDate localDate);
}
