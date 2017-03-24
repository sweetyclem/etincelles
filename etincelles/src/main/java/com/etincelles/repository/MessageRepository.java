package com.etincelles.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findById( Long id );

    List<Message> findByTitleContaining( String title );

    List<Message> findByTextContaining( String text );
}
