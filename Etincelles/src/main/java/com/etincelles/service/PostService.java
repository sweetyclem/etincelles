package com.etincelles.service;

import java.util.List;

import com.etincelles.entities.Post;

public interface PostService {
    Post findById( Long id );

    Post createPost( Post post );

    List<Post> searchAll( String text );

    List<Post> findAll();
}
