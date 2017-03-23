package com.etincelles.service;

import java.util.List;

import com.etincelles.entities.Message;

public interface MessageService {
    Message findById( Long id );

    List<Message> searchAll( String text );

    List<Message> findAll();
}
