package com.etincelles.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findById( Long id );

    List<Post> findByTitleContaining( String title );

    List<Post> findByTextContaining( String text );

    List<Post> findAllByOrderByDateDesc();
}
